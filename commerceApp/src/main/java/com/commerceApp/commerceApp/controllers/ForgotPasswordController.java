package com.commerceApp.commerceApp.controllers;

import com.commerceApp.commerceApp.dtos.ForgotPassword;
import com.commerceApp.commerceApp.services.ForgotPasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import javax.validation.Valid;
@RestController
public class ForgotPasswordController {
    @Autowired
    ForgotPasswordService forgotPasswordService;
    @PostMapping("/forgot-password")
    public ResponseEntity<String> getResetPasswordToken(@RequestParam String email, WebRequest webRequest) {
        return forgotPasswordService.initiatePasswordReset(email,webRequest);
    }
    /*
    @PostMapping("/reset-password")
    public String resetPassword(@RequestParam("token") String token, @Valid @RequestBody ForgotPassword passwords, WebRequest request) {
        return forgotPasswordService.resetPassword(token, passwords, request);
    }

     */
}