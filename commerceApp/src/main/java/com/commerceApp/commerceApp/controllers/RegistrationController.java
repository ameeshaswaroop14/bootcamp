package com.commerceApp.commerceApp.controllers;

import com.commerceApp.commerceApp.dtos.registrationDtos.CustomerRegistrationDto;
import com.commerceApp.commerceApp.dtos.registrationDtos.SellerRegistrationDto;

import com.commerceApp.commerceApp.services.*;
import com.commerceApp.commerceApp.util.responseDtos.BaseDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
@Api(value = "RegistrationController", description = "REST APIs related to Registration")
@RestController
public class RegistrationController {


    @Autowired
    ActivationService activationService;
    @Autowired
    RegistrationService registrationService;


    @ApiOperation(value = "To register customer", authorizations = { @Authorization(value="Bearer") })
    @PostMapping(value = "/register/customer",produces = "application/json")
    public ResponseEntity<BaseDto> registerCustomer(@Valid @RequestBody CustomerRegistrationDto customerRegistrationDto,@ApiIgnore WebRequest webRequest) {

        return registrationService.registerCustomer(customerRegistrationDto, webRequest);
    }

    @ApiOperation(value = "To activate customer by token", authorizations = { @Authorization(value="Bearer") })
    @GetMapping(value = "/activate/customer",produces = "application/json")
    public ResponseEntity<BaseDto> activateCustomer(@RequestParam("token") String token,@ApiIgnore WebRequest webRequest) {
        return activationService.activateUserByToken(token, webRequest);
    }


    @ApiOperation(value = "To register seller", authorizations = { @Authorization(value="Bearer") })
    @PostMapping(value = "/register/seller",produces = "application/json")
    public String registerSeller(@Valid @RequestBody SellerRegistrationDto sellerRegistrationDto) {
        return registrationService.registerSeller(sellerRegistrationDto);
    }


}


