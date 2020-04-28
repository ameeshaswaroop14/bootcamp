package com.commerceApp.commerceApp.services;

import com.commerceApp.commerceApp.models.User;
import com.commerceApp.commerceApp.models.tokens.VerificationToken;
import com.commerceApp.commerceApp.repositories.UserRepository;
import com.commerceApp.commerceApp.util.responseDtos.BaseDto;
import com.commerceApp.commerceApp.util.responseDtos.ErrorDto;
import com.commerceApp.commerceApp.util.responseDtos.ResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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



    public ResponseEntity<BaseDto> activateUserByToken(String token, WebRequest request){
        String message;
        BaseDto response;
        VerificationToken verificationToken = tokenService.getVerificationToken(token);
        if (verificationToken == null) {
            response=new ErrorDto("Invalid Operation","No user found with the given token");
            return new ResponseEntity<BaseDto>(response, HttpStatus.BAD_REQUEST);
        }

        // if token is expired
        User user = verificationToken.getUser();
        Calendar cal = Calendar.getInstance();
        if ((verificationToken.getExpiryDate().getTime() - cal.getTime().getTime()) <= 0) {

            response=new ErrorDto("Token Expired","Your activation link has been expired. We have sent a net link to your " +
                    "registered email.");

            String appUrl = request.getContextPath();
            tokenService.deleteVerificationToken(token);
            mailService.sendActivationLinkMail(appUrl, user, "Account Activation Link");

            return new ResponseEntity<BaseDto>(response, HttpStatus.BAD_REQUEST);
        }

        if(user.isActive()){
            message = "Your account is already active";
            response = new ResponseDto<>(null, message);
            return new ResponseEntity<BaseDto>(response, HttpStatus.OK);
        }

        user.setActive(true);
        tokenService.saveRegisteredUser(user);
        tokenService.deleteVerificationToken(token);
        message = "you have been activated successfully";
        response = new ResponseDto<>(null, message);
        return new ResponseEntity<BaseDto>(response, HttpStatus.OK);
    }



}
