package com.commerceApp.commerceApp.controllers;

import com.commerceApp.commerceApp.dtos.ForgotPassword;
import com.commerceApp.commerceApp.services.ForgotPasswordService;
import com.commerceApp.commerceApp.util.responseDtos.BaseDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
@Api(value = "ForgotPasswordController", description = "REST APIs related to Forgot Password")
@RestController
public class ForgotPasswordController {
    @Autowired
    ForgotPasswordService forgotPasswordService;
    @ApiOperation(value = "To get reset password token", authorizations = { @Authorization(value="Bearer") })
    @PostMapping(value = "/forgot-password",produces = "application/json")
    public ResponseEntity<BaseDto> getResetPasswordToken(@RequestParam String email, @ApiIgnore WebRequest webRequest) {
        return new ResponseEntity<>(forgotPasswordService.initiatePasswordReset(email, webRequest), HttpStatus.OK);

    }
    @ApiOperation(value = "To reset password", authorizations = { @Authorization(value="Bearer") })
    @PostMapping(value = "/reset-password",produces = "application/json")
    public ResponseEntity<BaseDto> resetPassword(@RequestParam("token") String token, @Valid @RequestBody ForgotPassword passwords,@ApiIgnore WebRequest request) {
        return new ResponseEntity<>(forgotPasswordService.resetPassword(token, passwords, request),HttpStatus.OK);

    }


}