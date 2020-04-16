package com.commerceApp.commerceApp.services;

import com.commerceApp.commerceApp.Models.ForgotPasswordToken;
import com.commerceApp.commerceApp.Models.User;
import com.commerceApp.commerceApp.dtos.ForgotPassword;
import com.commerceApp.commerceApp.repositories.CustomerRepository;
import com.commerceApp.commerceApp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.WebRequest;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

@Service
public class ForgotPasswordService {
    @Autowired
    MailService mailService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    TokenService tokenService;


    public ResponseEntity <String> initiatePasswordReset(String email, WebRequest request){
        String message;
        User user = userRepository.findByEmail(email);
        if(user==null)
            return new ResponseEntity<>("The given email does not exist",HttpStatus.NOT_FOUND);

         if(!user.isActive() || user.isLocked()) {
            return new ResponseEntity<>("User is either deactivated or locked.", HttpStatus.CONFLICT);
        }

        String token =tokenService.createForgotPasswordToken(user);
        sendForgotPasswordInitiationMail(user, token);
        return new ResponseEntity<>("An email has been sent to your registered user account", HttpStatus.OK);
    }

    public void sendForgotPasswordInitiationMail(User user, String token){

        String email = user.getEmail();
        String subject = "Password Reset Link";
        String passwordResetUrl = "http://localhost:8080" + "/reset-password?token=" + token;
        String emailMessage = "please click on this link to reset your password";
        String emailBody = emailMessage + "\r\n" + passwordResetUrl;
        System.out.println(passwordResetUrl);

        mailService.sendEmail(email, subject, emailBody);
    }

    public ResponseEntity<String> resetPassword(String token, ForgotPassword passwords, WebRequest request) {
        ForgotPasswordToken forgotPasswordToken = tokenService.getForgotPasswordToken(token);
        if (forgotPasswordToken == null) {
            return new ResponseEntity<>("Invalid token", HttpStatus.BAD_REQUEST);
        }

        User user = forgotPasswordToken.getUser();
        Calendar cal = Calendar.getInstance();
        if ((forgotPasswordToken.getExpiryDate().getTime() - cal.getTime().getTime()) <= 0) {

            String appUrl = request.getContextPath();
            tokenService.deleteForgotToken(token);
            return new ResponseEntity<>("Token expired", HttpStatus.BAD_REQUEST);
        }
        user.setPassword(passwords.getPassword());
        userRepository.save(user);
        tokenService.deleteForgotToken(token);
        return new ResponseEntity<>("Password changed successfully", HttpStatus.OK);
    }



}