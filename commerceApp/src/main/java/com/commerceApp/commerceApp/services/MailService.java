package com.commerceApp.commerceApp.services;

import com.commerceApp.commerceApp.models.product.Product;
import com.commerceApp.commerceApp.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Async
@Service
public class MailService {
    @Autowired
    JavaMailSender javaMailSender;
    @Autowired
    TokenService tokenService;


    public void sendEmail(String email, String subject,String text){
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(email);
        mailMessage.setSubject(subject);
        mailMessage.setText(text);

        javaMailSender.send(mailMessage);
    }
    public void sendActivationLinkMail(String appUrl, User user, String subject) {

        String token = tokenService.createVerficationToken(user);

        String email = user.getEmail();
        String confirmationUrl = "http://localhost:8080" + appUrl +
                "/activate/customer?token=" + token;
        String message = "please activate your account \r\n" + confirmationUrl;
        sendEmail(email, subject, message);
    }
    public void sendForgotPasswordInitiationMail(User user, String token){

        String email = user.getEmail();
        String subject = "Password Reset Link";
        String passwordResetUrl = "http://localhost:8080" + "/reset-password?token=" + token;
        String emailMessage = "please click on this link to reset your password";
        String emailBody = emailMessage + "\r\n" + passwordResetUrl;
        System.out.println(passwordResetUrl);

        sendEmail(email, subject, emailBody);
    }
    public void sendPasswordResetConfirmationMail(String email) {
        String subject = "Password Reset Successfully";
        String message = "the password for your account has been reset successfully";
        sendEmail(email, subject, message);
    }
    public void sendProductCreationMail(String email, Product product) {
        String subject = "Product created";
        String content = "A product with following details has been created - \n" +
                "name - " + product.getName() + "\n" +
                "category - " + product.getCategory().getName() + "\n" +
                "brand - " + product.getBrand() + "\n" +
                "description - " + product.getDescription();
        sendEmail(email, subject, content);
    }
    public void sendProductActivationMail(String email) {

        sendEmail(email, "Product Activation", "Product activation successfully done");
    }

    public void sendProductDeactivationMail(String email, Product product) {
        String subject = "product deactivation";
        String content = "The product has been deleted. product details are as follows - \n" +
                "name - " + product.getName() + "\n" +
                "category - " + product.getCategory().getName() + "\n" +
                "brand - " + product.getBrand() + "\n" +
                "description - " + product.getDescription();
        sendEmail(email, subject, content);
    }

    public void acknowledgementEmail(String email){
        String subject="Registration confirmation";
        String text="Your account is awaited for confirmation";
        sendEmail(email,subject,text);
    }



}


