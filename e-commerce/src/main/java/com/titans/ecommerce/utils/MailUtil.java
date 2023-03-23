package com.titans.ecommerce.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;


@Component
public class MailUtil {
    private final JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String from;

    @Autowired
    public MailUtil(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendCodeMail(String to, String code) throws MailException {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(to);
        message.setSubject("E-Commerce Titans: password reset");
        String text = "Dear user: The verification code is " + code + ", it is valid within 5 minutes.";
        message.setText(text);

        mailSender.send(message);
    }

    public void sendPasswordMail(String to, String password) throws MailException {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(to);
        message.setSubject("E-Commerce Titans: password reset");
        String text = "Dear user：The password of your account is " + password;
        message.setText(text);

        mailSender.send(message);
    }
}
