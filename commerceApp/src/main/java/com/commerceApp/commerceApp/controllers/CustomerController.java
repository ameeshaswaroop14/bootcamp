package com.commerceApp.commerceApp.controllers;

import com.commerceApp.commerceApp.dtos.AddressDto;
import com.commerceApp.commerceApp.dtos.profileDtos.CustomerViewProfileDto;
import com.commerceApp.commerceApp.services.CustomerService;
import com.commerceApp.commerceApp.services.UserService;
import io.swagger.annotations.ApiOperation;
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
    @Autowired
    UserService userService;

    @ApiOperation("To get customer profile")
    @GetMapping("/customer/profile")
    public CustomerViewProfileDto getProfileDetails(HttpServletRequest request) {
        Principal principal = request.getUserPrincipal();
        String username = principal.getName();
        return customerService.getUserProfile(username);
    }

    @ApiOperation("To get customer addresses")
    @GetMapping("/customer/addresses")
    public Set<AddressDto> getCustomerAddresses(HttpServletRequest request) {
        Principal principal = request.getUserPrincipal();
        String username = principal.getName();
        return customerService.getCustomerAddresses(username);
    }

    @ApiOperation("To add new address")
    @PostMapping("/customer/addresses")
    public ResponseEntity addNewAddress(@Valid @RequestBody AddressDto addressDto, HttpServletRequest request) {
        Principal principal = request.getUserPrincipal();
        String username = principal.getName();
        return customerService.addNewAddress(username, addressDto);
    }

    @ApiOperation("To update user profile")
    @PatchMapping("/customer/profile")
    public ResponseEntity updateProfile(@Valid @RequestBody CustomerViewProfileDto customerViewProfileDto, HttpServletRequest httpServletRequest) {
        Principal principal = httpServletRequest.getUserPrincipal();
        String username = principal.getName();
        return customerService.updateProfile(username, customerViewProfileDto);
    }

    @ApiOperation("Delete address by id")
    @DeleteMapping("/customer/addresses/{id}")
    public ResponseEntity<String> deleteAddressById(@PathVariable Long id, HttpServletRequest request) {
        Principal principal = request.getUserPrincipal();
        String username = principal.getName();
        return customerService.deleteAddress(username, id);
    }

    @ApiOperation("update address by id")
    @PatchMapping("/updateAddress/{id}")
    public ResponseEntity<String> updateCustomerAddress(@Valid @RequestBody AddressDto addressDto, @PathVariable Long id, HttpServletRequest httpServletRequest) {
        Principal principal = httpServletRequest.getUserPrincipal();
        String username = principal.getName();
        return userService.updateAddressById(username, id, addressDto);

    }


}
