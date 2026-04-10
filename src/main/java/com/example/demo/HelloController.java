package com.example.demo;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
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
@CrossOrigin(origins = "*") // change origin for production
public class HelloController {

	private final ContactRepository contactRepository;
    private final JavaMailSender mailSender;
    
    
    @Autowired
    public HelloController(ContactRepository contactRepository,
                           JavaMailSender mailSender) {
        this.contactRepository = contactRepository;
        this.mailSender = mailSender;
    }

 // NEW: Root endpoint
    @GetMapping("/")
    public String home() {
        return "Hello! Your Spring Boot app is running!";
    }
    
    
    @PostMapping("/contact")
    public ResponseEntity<String> receiveContact(@RequestBody Contact contact) {
        System.out.println("NAME = " + contact.getName());
        System.out.println("EMAIL = " + contact.getEmail());
        System.out.println("MESSAGE = " + contact.getMessage());
        System.out.println("POST /api/contact called");
        contactRepository.save(contact);
        return ResponseEntity.ok("Saved successfully");
    }

    
    @GetMapping("/contacts")
    public List<Contact> getAllContacts() {
        return contactRepository.findAll();
    }
    
}
