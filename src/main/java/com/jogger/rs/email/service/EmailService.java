package com.jogger.rs.email.service;

import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@CommonsLog
public class EmailService implements EmailServiceInterface{

    private JavaMailSender mailSender;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    @Async
    public void sendEmailWithUsernameAndPassword(String email, String username, String password) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(EmailLabels.FROM);
        message.setTo(email);
        message.setSubject(EmailLabels.SUBJECT);
        message.setText(String.format(EmailLabels.BODY, username, password));
        mailSender.send(message);
        log.info("Mejl za kreiranje naloga uspesno poslat na email " + email);
    }
}
