package com.assesment.Multisig.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService{
    @Autowired
    private JavaMailSender emailSender;
    @Override
    public void sendNotifiedMessage(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
//        message.setFrom("primeuser1876@gmail.com");
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        emailSender.send(message);
    }
}
