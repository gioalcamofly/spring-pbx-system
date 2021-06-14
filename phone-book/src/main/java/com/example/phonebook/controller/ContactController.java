package com.example.phonebook.controller;

import com.example.phonebook.exceptions.ContactNotFoundException;
import com.example.phonebook.model.Contact;
import com.example.phonebook.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ContactController {

    @Autowired
    private ContactRepository contactRepository;

    @GetMapping("/phone-book/contacts")
    public List<Contact> getContacts() {
        return (List<Contact>) contactRepository.findAll();
    }

    @GetMapping("/phone-book/contact/{id}")
    public Contact getContact(@PathVariable Long id) {
        return contactRepository.findById(id)
                .orElseThrow(() -> new ContactNotFoundException("id", id));
    }

    @GetMapping("/phone-book/contact-number/{number}")
    public Contact getContactByNumber(@PathVariable String number) {
        return contactRepository.findByNumber(number)
                .orElseThrow(() -> new ContactNotFoundException("number ", number));
    }

    @PostMapping("/phone-book/contact")
    @ResponseStatus(HttpStatus.CREATED)
    public Contact createContact(@RequestBody Contact contact) {
        return contactRepository.save(contact);
    }

    @PutMapping("/phone-book/contact")
    public Contact updateContact(@RequestBody Contact contact) {
        return contactRepository.findById(contact.getId())
                .map(c -> {
                    if (contact.getName() != null) c.setName(contact.getName());
                    if (contact.getEmail() != null) c.setEmail(contact.getEmail());
                    if (contact.getNumber() != null) c.setNumber(contact.getNumber());
                    return contactRepository.save(c);
                })
                .orElseThrow(() -> new ContactNotFoundException("id", contact.getId()));
    }

    @DeleteMapping("/phone-book/contact/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteContact(@PathVariable Long id) {
        contactRepository.deleteById(id);
    }


}
