package com.commerceApp.commerceApp.services;

import com.commerceApp.commerceApp.models.tokens.ForgotPasswordToken;
import com.commerceApp.commerceApp.models.User;
import com.commerceApp.commerceApp.dtos.ForgotPassword;
import com.commerceApp.commerceApp.repositories.userRepos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.WebRequest;

import java.util.Calendar;

@Service
public class ForgotPasswordService {
    @Autowired
    MailService mailService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    TokenService tokenService;
    @Autowired
    TokenStore tokenStore;


    public ResponseEntity <String> initiatePasswordReset(String email, WebRequest request){
        String message;
        System.out.println(email);

        User user = userRepository.findByEmail(email);
        if(user==null)
            return new ResponseEntity<>("The given email does not exist",HttpStatus.NOT_FOUND);

         if(!user.isActive() || user.isLocked()) {
            return new ResponseEntity<>("User is either deactivated or locked.", HttpStatus.CONFLICT);
        }

        String token =tokenService.createForgotPasswordToken(user);
        mailService.sendForgotPasswordInitiationMail(user, token);
        return new ResponseEntity<>("An email has been sent to your registered user account", HttpStatus.OK);
    }


    public ResponseEntity<String> resetPassword(String token, ForgotPassword password, WebRequest request){

        ForgotPasswordToken forgotPasswordToken = tokenService.getForgotPasswordToken(token);
        if (forgotPasswordToken == null) {
            return new ResponseEntity<>("Invalid token", HttpStatus.BAD_REQUEST);
        }

        User user = forgotPasswordToken.getUser();
        Calendar cal = Calendar.getInstance();
        if ((forgotPasswordToken.getExpiryDate().getTime() - cal.getTime().getTime()) <= 0) {

            String appUrl = request.getContextPath();
            tokenService.deleteForgotToken(token);
            return new ResponseEntity<>("Token Expired", HttpStatus.BAD_REQUEST);
        }

        user.setPassword(password.getPassword());
        tokenService.saveRegisteredUser(user);
        tokenService.deleteForgotToken(token);

        logoutUser(user.getEmail(), request);
        mailService.sendPasswordResetConfirmationMail(user.getEmail());
        return new ResponseEntity<>("Password changed successfully", HttpStatus.OK);
    }

    public void logoutUser(String email, WebRequest request){
        String authHeader = request.getHeader("Authorization");
        if (authHeader != null) {
            String tokenValue = authHeader.replace("Bearer", "").trim();
            OAuth2AccessToken accessToken = tokenStore.readAccessToken(tokenValue);
            tokenStore.removeAccessToken(accessToken);
        }
    }



}