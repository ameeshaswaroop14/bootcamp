package com.commerceApp.commerceApp.services;

import com.commerceApp.commerceApp.models.tokens.ForgotPasswordToken;
import com.commerceApp.commerceApp.models.User;
import com.commerceApp.commerceApp.dtos.ForgotPassword;
import com.commerceApp.commerceApp.repositories.userRepos.UserRepository;
import com.commerceApp.commerceApp.util.responseDtos.BaseDto;
import com.commerceApp.commerceApp.util.responseDtos.ErrorDto;
import com.commerceApp.commerceApp.util.responseDtos.ResponseDto;
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


    public BaseDto initiatePasswordReset(String email, WebRequest request){
        String message;
        System.out.println(email);

        User user = userRepository.findByEmail(email);
        if(user==null)
            return new ErrorDto("Not found","Given email does not exist");

         if(!user.isActive() || user.isLocked()) {
            return new ErrorDto("Conflict","User is deactivated or locked");
        }

        String token =tokenService.createForgotPasswordToken(user);
        mailService.sendForgotPasswordInitiationMail(user, token);
        return new ResponseDto<>("An email has been sent to your email id",null);
    }


    public BaseDto resetPassword(String token, ForgotPassword password, WebRequest request){

        ForgotPasswordToken forgotPasswordToken = tokenService.getForgotPasswordToken(token);
        if (forgotPasswordToken == null) {
            return new ErrorDto("Invalid token","");
        }

        User user = forgotPasswordToken.getUser();
        Calendar cal = Calendar.getInstance();
        if ((forgotPasswordToken.getExpiryDate().getTime() - cal.getTime().getTime()) <= 0) {

            String appUrl = request.getContextPath();
            tokenService.deleteForgotToken(token);
            return new ErrorDto("token expired","");
        }

        user.setPassword(password.getPassword());
        tokenService.saveRegisteredUser(user);
        tokenService.deleteForgotToken(token);

        logoutUser(user.getEmail(), request);
        mailService.sendPasswordResetConfirmationMail(user.getEmail());
        return new ResponseDto<>("Password Changed",null);
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