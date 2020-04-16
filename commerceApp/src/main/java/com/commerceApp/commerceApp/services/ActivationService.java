package com.commerceApp.commerceApp.services;

import com.commerceApp.commerceApp.Models.User;
import com.commerceApp.commerceApp.Models.VerificationToken;
import com.commerceApp.commerceApp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.WebRequest;

import java.util.Calendar;

@Service
public class ActivationService {
    @Autowired
    TokenService tokenService;
    @Autowired
    MailService mailService;
    @Autowired
    UserRepository userRepository;


    public String activateUserByToken(String token, WebRequest webRequest) {
        VerificationToken verificationToken = tokenService.getVerificationToken(token);
        if (verificationToken == null) {
            return "Invalid token";
        }
        String message;
        User user = verificationToken.getUser();
        Calendar cal = Calendar.getInstance();
        if ((verificationToken.getExpiryDate().getTime() - cal.getTime().getTime()) <= 0) {

            message = "Your activation link has been expired. We have sent a net link to your " +
                    "registered email.";
            String appUrl = webRequest.getContextPath();
            tokenService.deleteVerificationToken(token);
            sendActivationLinkMail(appUrl, user, "Account Re-Activation Link");

        }
        user.setActive(true);
        userRepository.save(user);
        tokenService.deleteVerificationToken(token);
        message = "Activated";
        return message;
    }

    public void sendActivationLinkMail(String appUrl, User user, String subject) {

        String token = tokenService.createVerficationToken(user);

        String email = user.getEmail();
        String confirmationUrl = "http://localhost:8080" + appUrl +
                "/activate/customer?token=" + token;
        String message = "please activate your account \r\n" + confirmationUrl;
        mailService.sendEmail(email, subject, message);
    }

}
