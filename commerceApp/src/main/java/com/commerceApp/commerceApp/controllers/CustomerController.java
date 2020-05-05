package com.commerceApp.commerceApp.controllers;

import com.commerceApp.commerceApp.dtos.AddressDto;
import com.commerceApp.commerceApp.dtos.profileDtos.CustomerViewProfileDto;
import com.commerceApp.commerceApp.services.CustomerService;
import com.commerceApp.commerceApp.services.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.Principal;
import java.util.Set;
@Api(value = "CustomerController", description = "REST APIs related to customer")
@RestController
public class CustomerController {
    @Autowired
    CustomerService customerService;
    @Autowired
    UserService userService;

    @ApiOperation(value = "To get customer profile", authorizations = { @Authorization(value="Bearer") })
    @GetMapping(value = "/customer/profile",produces = "application/json")
    public CustomerViewProfileDto getProfileDetails(@ApiIgnore HttpServletRequest request) {
        Principal principal = request.getUserPrincipal();
        String username = principal.getName();
        return customerService.getUserProfile(username);
    }

    @ApiOperation(value = "To get customer's address", authorizations = { @Authorization(value="Bearer") })
    @GetMapping(value = "/customer/addresses",produces = "application/json")

    public Set<AddressDto> getCustomerAddresses(@ApiIgnore HttpServletRequest request) {
        Principal principal = request.getUserPrincipal();
        String username = principal.getName();
        return customerService.getCustomerAddresses(username);
    }

    @ApiOperation(value = "To add customer's address", authorizations = { @Authorization(value="Bearer") })
    @PostMapping(value = "/customer/addresses",produces = "application/json")
    public ResponseEntity addNewAddress(@Valid @RequestBody AddressDto addressDto, HttpServletRequest request) {
        Principal principal = request.getUserPrincipal();
        String username = principal.getName();
        return customerService.addNewAddress(username, addressDto);
    }


    @ApiOperation(value = "Update customer's profile", authorizations = { @Authorization(value="Bearer") })
    @PatchMapping(value = "/customer/profile",produces = "application/json")
    public ResponseEntity updateProfile(@Valid @RequestBody CustomerViewProfileDto customerViewProfileDto,@ApiIgnore HttpServletRequest httpServletRequest) {
        Principal principal = httpServletRequest.getUserPrincipal();
        String username = principal.getName();
        return customerService.updateProfile(username, customerViewProfileDto);
    }

    @ApiOperation(value = "To delete customer's address", authorizations = { @Authorization(value="Bearer") })
    @DeleteMapping(value = "/customer/addresses/{id}",produces = "application/json")
    public ResponseEntity<String> deleteAddressById(@PathVariable Long id, @ApiIgnore HttpServletRequest request) {
        Principal principal = request.getUserPrincipal();
        String username = principal.getName();
        return customerService.deleteAddress(username, id);
    }

    @ApiOperation(value = "To update customer's address", authorizations = { @Authorization(value="Bearer") })
    @PatchMapping(value = "/updateAddress/{id}",produces = "application/json")
    public ResponseEntity<String> updateCustomerAddress(@Valid @RequestBody AddressDto addressDto, @PathVariable Long id, @ApiIgnore HttpServletRequest httpServletRequest) {
        Principal principal = httpServletRequest.getUserPrincipal();
        String username = principal.getName();
        return userService.updateAddressById(username, id, addressDto);

    }


}
