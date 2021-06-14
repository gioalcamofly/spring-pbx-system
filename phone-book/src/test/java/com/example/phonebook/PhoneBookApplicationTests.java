package com.example.phonebook;

import com.example.phonebook.model.Contact;
import com.example.phonebook.repository.ContactRepository;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Transactional
class PhoneBookApplicationTests {

	@Autowired
	private MockMvc mvc;

	@Autowired
	ContactRepository contactRepository;

	@Test
	@Order(1)
	public void testCreateProduct() throws Exception {
		this.mvc.perform(post("/phone-book/contact")
				.contentType(MediaType.APPLICATION_JSON)
				.content(getCreateContactData().toString())
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated());
	}

	@Test
	@Order(2)
	public void testGetContactById() throws Exception {
		Contact contact = ((List<Contact>) contactRepository.findAll()).get(0);
		this.mvc.perform(get("/phone-book/contact/" + contact.getId())
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().json(contact.toJsonString()));
	}

	@Test
	@Order(3)
	public void testGetContactByNumber() throws Exception {
		Contact contact = ((List<Contact>) contactRepository.findAll()).get(0);
		this.mvc.perform(get("/phone-book/contact-number/" + contact.getNumber())
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().json(contact.toJsonString()));
	}

	@Test
	@Order(4)
	public void testGetContacts() throws Exception {
		List<Contact> contacts = (List<Contact>) contactRepository.findAll();
		List<String> contactsString = contacts.stream().map(contact -> contact.toJsonString()).collect(Collectors.toList());
		this.mvc.perform(get("/phone-book/contacts")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().json(contactsString.toString()));
	}

	@Test
	@Order(5)
	public void testUpdateContact() throws Exception {
		this.mvc.perform(put("/phone-book/contact")
			.contentType(MediaType.APPLICATION_JSON)
			.content(getUpdateContactData().toString())
			.accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk());
	}

	@Test
	@Order(6)
	public void testDeleteContact() throws Exception {
		Contact contact = ((List<Contact>) contactRepository.findAll()).get(0);
		this.mvc.perform(delete("/phone-book/contact/" + contact.getId()))
				.andExpect(status().isNoContent());
	}

	//Utility methods

	private JSONObject getCreateContactData() throws JSONException {
		JSONObject contactData = new JSONObject();
		contactData.put("name", "Jose");
		contactData.put("email", "jose@gmail.com");
		contactData.put("number", "635345234");
		return contactData;
	}

	private JSONObject getUpdateContactData() throws JSONException {
		Contact contact = ((List<Contact>) contactRepository.findAll()).get(0);
		JSONObject contactData = new JSONObject();
		contactData.put("id", contact.getId());
		contactData.put("name", "Pepe");
		contactData.put("email", "pepe@gmail.com");
		return contactData;
	}

}
