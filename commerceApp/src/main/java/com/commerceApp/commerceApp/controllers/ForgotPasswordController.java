package com.commerceApp.commerceApp.controllers;

import com.commerceApp.commerceApp.dtos.ForgotPassword;
import com.commerceApp.commerceApp.services.ForgotPasswordService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import javax.validation.Valid;
@RestController
public class ForgotPasswordController {
    @Autowired
    ForgotPasswordService forgotPasswordService;
    @ApiOperation("To get reset password token")
    @PostMapping("/forgot-password")
    public ResponseEntity<String> getResetPasswordToken(@RequestParam String email, WebRequest webRequest) {
        return forgotPasswordService.initiatePasswordReset(email,webRequest);
    }
    @ApiOperation("To reset password")
    @PostMapping("/reset-password")
    public ResponseEntity<String> resetPassword(@RequestParam("token") String token, @Valid @RequestBody ForgotPassword passwords, WebRequest request) {
        return forgotPasswordService.resetPassword(token, passwords, request);
    }


}