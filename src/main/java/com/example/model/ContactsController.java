package com.example.model;

import java.util.List;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class ContactsController {

	private ContactRepository contactRepository;

	public ContactsController(ContactRepository contactRepository) {
		super();
		this.contactRepository = contactRepository;
	}

	@GetMapping("/contacts")
	public List<Contact> getContact() {
		return (List<Contact>) contactRepository.findAll();
	}

	@PostMapping("/contacts")
	public Contact createContact(@Valid @RequestBody Contact newContact) {
		Contact result = contactRepository.save(newContact);
		return result;
	}

	@PutMapping("/contacts/{id}")
	public Contact updateContact(@Valid @RequestBody Contact newContact, @PathVariable Long id) {

		return contactRepository.findById(id).map(contact -> {
			contact.setFirstName(newContact.getFirstName());
			contact.setLastName(newContact.getLastName());
			contact.setEmail(newContact.getEmail());
			return contactRepository.save(contact);
		}).orElseGet(() -> {
			return contactRepository.save(newContact);
		});
	}

	@DeleteMapping("contacts/{id}")
	public List<Contact> remove(@PathVariable Long id) {
		contactRepository.deleteById(id);
		return (List<Contact>) contactRepository.findAll();
	}
}
