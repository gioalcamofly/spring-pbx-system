package com.example.phonebook.repository;

import com.example.phonebook.model.Contact;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ContactRepository extends CrudRepository<Contact, Long> {

    Optional<Contact> findByNumber(String number);
}
