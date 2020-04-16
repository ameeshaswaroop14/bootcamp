package com.commerceApp.commerceApp.controllers;

import com.commerceApp.commerceApp.dtos.AddressDto;
import com.commerceApp.commerceApp.dtos.CustomerViewProfileDto;
import com.commerceApp.commerceApp.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.Principal;
import java.util.Set;

@RestController
public class CustomerController {
    @Autowired
    CustomerService customerService;
    @GetMapping("/customer/profile")
    public CustomerViewProfileDto getProfileDetails(HttpServletRequest request){
        Principal principal = request.getUserPrincipal();
        String username = principal.getName();
        return customerService.getUserProfile(username);
    }
    @GetMapping("/customer/addresses")
    public Set<AddressDto> getCustomerAddresses(HttpServletRequest request){
        Principal principal = request.getUserPrincipal();
        String username = principal.getName();
        return customerService.getCustomerAddresses(username);
    }

    @PostMapping("/customer/addresses")
    public ResponseEntity addNewAddress(@Valid @RequestBody AddressDto addressDto, HttpServletRequest request){
        Principal principal = request.getUserPrincipal();
        String username = principal.getName();
        return customerService.addNewAddress(username,addressDto);
    }


    @DeleteMapping("/customer/addresses/{id}")
    public ResponseEntity<String> deleteAddressById(@PathVariable Long id, HttpServletRequest request){
        Principal principal = request.getUserPrincipal();
        String username = principal.getName();
        return customerService.deleteAddress(username, id);
    }








}
