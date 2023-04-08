package com.titans.ecommerce.utils;

import com.titans.ecommerce.models.entity.Product;
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

    public void sendOrderCreatedNotificationEmail(String recipient, String product, Integer orderId) throws MailException {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setFrom(from);
        msg.setTo(recipient);
        msg.setSubject("Confirmation from E-Commerce Titans: Order Created");
        String text = "Dear user, \n\tThe order of the product: " + product + " is created. Your order id is " + orderId + ". " +
                "\n\t Thanks for using E-Commerce Titans!";
        msg.setText(text);
        mailSender.send(msg);
    }

    public void sendOrderApprovalNotificationEmail(String recipient, String product, Integer orderId) throws MailException {
        SimpleMailMessage buyerMsg = new SimpleMailMessage();
        buyerMsg.setFrom(from);
        buyerMsg.setTo(recipient);
        buyerMsg.setSubject("Notificatoin from E-Commerce Titans: Order Approved!");
        String text = "Dear user, \n\tThe order of the product: " + product + " is approved by the seller. The order id is " + orderId + ". " +
                "\n\t Thanks for using E-Commerce Titans!";
        buyerMsg.setText(text);
        mailSender.send(buyerMsg);
    }

    public void sendOrderDenialNotificationEmail(String recipient, String product, Integer orderId) throws MailException {
        SimpleMailMessage buyerMsg = new SimpleMailMessage();
        buyerMsg.setFrom(from);
        buyerMsg.setTo(recipient);
        buyerMsg.setSubject("Notificatoin from E-Commerce Titans: Order Denied!");
        String text = "Dear user, \n\tThe order of the product: " + product + " is denied by the seller. The order id is " + orderId + ". " +
                "\n\t Thanks for using E-Commerce Titans!";
        buyerMsg.setText(text);
        mailSender.send(buyerMsg);
    }

}
