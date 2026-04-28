package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Contact;
import com.example.demo.repository.ContactRepository;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class HelloController {

    private final ContactRepository contactRepository;
    private final EmailService emailService;

    @Autowired
    public HelloController(ContactRepository contactRepository, EmailService emailService) {
        this.contactRepository = contactRepository;
        this.emailService = emailService;
    }

    @GetMapping("/")
    public String home() {
        return "Hello! Your Spring Boot app is running!";
    }

    @PostMapping("/contact")
    public ResponseEntity<String> receiveContact(@RequestBody ContactRequest req) {
        try {
            Contact contact = new Contact();
            contact.setName(req.getName());
            contact.setEmail(req.getEmail());
            contact.setMessage(req.getMessage());
            contactRepository.save(contact);
            emailService.sendContactEmail(req);
            return ResponseEntity.ok("Saved and email sent successfully");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error: " + e.getMessage());
        }
    }

    @GetMapping("/contacts")
    public List<Contact> getAllContacts() {
        return contactRepository.findAll();
    }
}