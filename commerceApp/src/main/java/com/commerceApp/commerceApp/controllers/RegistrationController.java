package com.commerceApp.commerceApp.controllers;

import com.commerceApp.commerceApp.Models.Customer;
import com.commerceApp.commerceApp.Models.Seller;
import com.commerceApp.commerceApp.dtos.CustomerRegistrationDto;
import com.commerceApp.commerceApp.dtos.SellerRegistrationDto;

import com.commerceApp.commerceApp.exceptions.EmailAlreadyExistsException;
import com.commerceApp.commerceApp.repositories.CustomerRepository;
import com.commerceApp.commerceApp.repositories.SellerRepository;
import com.commerceApp.commerceApp.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
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



    @PostMapping("/register/customer")
    public String registerCustomer(@Valid @RequestBody CustomerRegistrationDto customerRegistrationDto, WebRequest webRequest) {

        return registrationService.registerCustomer(customerRegistrationDto,webRequest);
    }

    @GetMapping("/activate/customer")
    public String activateCustomer(@RequestParam("token") String token, WebRequest webRequest){
       return activationService.activateUserByToken(token,webRequest);
    }

    @PostMapping("/register/seller")
    public String registerSeller(@Valid @RequestBody SellerRegistrationDto sellerRegistrationDto){
        return registrationService.registerSeller(sellerRegistrationDto);
    }



}


