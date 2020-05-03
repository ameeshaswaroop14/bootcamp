package com.commerceApp.commerceApp.services;

import com.commerceApp.commerceApp.models.User;
import com.commerceApp.commerceApp.models.tokens.VerificationToken;
import com.commerceApp.commerceApp.repositories.UserRepository;
import com.commerceApp.commerceApp.util.responseDtos.BaseDto;
import com.commerceApp.commerceApp.util.responseDtos.ErrorDto;
import com.commerceApp.commerceApp.util.responseDtos.ResponseDto;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
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
    @Autowired
    MessageSource messageSource;


    @ApiOperation("Service to activate user by token")
    public ResponseEntity<BaseDto> activateUserByToken(String token, WebRequest request) {
        String message;
        String error;
        BaseDto response;
        VerificationToken verificationToken = tokenService.getVerificationToken(token);
        if (verificationToken == null) {
            error=messageSource.getMessage("message.invalidoperation",null,request.getLocale());
            message=messageSource.getMessage("message.usernotfoundwiththegiventoken",null,request.getLocale());
            response = new ErrorDto(error,message);
            return new ResponseEntity<BaseDto>(response, HttpStatus.BAD_REQUEST);
        }

        // if token is expired
        User user = verificationToken.getUser();
        Calendar cal = Calendar.getInstance();
        if ((verificationToken.getExpiryDate().getTime() - cal.getTime().getTime()) <= 0) {
            message=messageSource.getMessage("message.tokenexpire",null,request.getLocale());
            error=messageSource.getMessage("message.reactivationtoken",null,request.getLocale());
            response = new ErrorDto(error, message);
            String appUrl = request.getContextPath();
            tokenService.deleteVerificationToken(token);
            mailService.sendActivationLinkMail(appUrl, user, "Account Activation Link", request.getLocale());

            return new ResponseEntity<BaseDto>(response, HttpStatus.BAD_REQUEST);
        }

        if (user.isActive()) {
            message = messageSource.getMessage("message.accountactive",null,request.getLocale());
            response = new ResponseDto<>(null, message);
            return new ResponseEntity<BaseDto>(response, HttpStatus.OK);
        }

        user.setActive(true);
        tokenService.saveRegisteredUser(user);
        tokenService.deleteVerificationToken(token);
        message = messageSource.getMessage("message.accountactivated",null,request.getLocale());
        response = new ResponseDto<>(null, message);
        return new ResponseEntity<BaseDto>(response, HttpStatus.OK);
    }


}
