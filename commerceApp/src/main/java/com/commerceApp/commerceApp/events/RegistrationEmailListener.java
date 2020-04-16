package com.commerceApp.commerceApp.events;

import com.commerceApp.commerceApp.Models.User;
import com.commerceApp.commerceApp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.util.UUID;
/*
@Component
public class RegistrationEmailListener implements ApplicationListener<OnRegistrationSuccessEvent> {
    @Autowired
    UserService userService;
    @Autowired
    JavaMailSender javaMailSender;


    @Override
    public void onApplicationEvent(OnRegistrationSuccessEvent event) {
        this.confirmRegistration(event);

    }
    public void confirmRegistration(OnRegistrationSuccessEvent event){
        User user=event.getUser();
        String token= UUID.randomUUID().toString();
        userService.createVerificationToken(user,token);
        String recipient=user.getEmail();
        String url= event.getAppUrl() + "/confirmRegistration?token=" +token ;

        SimpleMailMessage simpleMailMessage=new SimpleMailMessage();
        simpleMailMessage.setTo(recipient);
        simpleMailMessage.setText("http://localhost:8080" + url);
        javaMailSender.send(simpleMailMessage);

    }

}

 */
