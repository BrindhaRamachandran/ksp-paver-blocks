package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Value("${app.mail.to}")
    private String businessEmail;

    @Value("${spring.mail.username}")
    private String fromEmail;

    public void sendContactEmail(ContactRequest req) {
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(businessEmail);
        mail.setFrom(fromEmail);  // ADD THIS
        mail.setSubject("New Contact Form Message - KSP Pavers");
        mail.setText(
            "Name: " + req.getName() + "\n" +
            "Email: " + req.getEmail() + "\n\n" +
            "Message:\n" + req.getMessage()
        );
        mailSender.send(mail);
    }
}