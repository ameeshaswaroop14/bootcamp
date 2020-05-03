package com.commerceApp.commerceApp.controllers;

import com.commerceApp.commerceApp.dtos.registrationDtos.CustomerRegistrationDto;
import com.commerceApp.commerceApp.dtos.registrationDtos.SellerRegistrationDto;

import com.commerceApp.commerceApp.services.*;
import com.commerceApp.commerceApp.util.responseDtos.BaseDto;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import javax.validation.Valid;

@RestController
public class RegistrationController {


    @Autowired
    ActivationService activationService;
    @Autowired
    RegistrationService registrationService;

    @ApiOperation("to register a customer")
    @PostMapping("/register/customer")
    public ResponseEntity<BaseDto> registerCustomer(@Valid @RequestBody CustomerRegistrationDto customerRegistrationDto, WebRequest webRequest) {

        return registrationService.registerCustomer(customerRegistrationDto, webRequest);
    }

    @ApiOperation("Activate customer using token")
    @GetMapping("/activate/customer")
    public ResponseEntity<BaseDto> activateCustomer(@RequestParam("token") String token, WebRequest webRequest) {
        return activationService.activateUserByToken(token, webRequest);
    }

    @ApiOperation("Register a seller")
    @PostMapping("/register/seller")
    public String registerSeller(@Valid @RequestBody SellerRegistrationDto sellerRegistrationDto) {
        return registrationService.registerSeller(sellerRegistrationDto);
    }


}


