package com.commerceApp.commerceApp.controllers;

import com.commerceApp.commerceApp.dtos.AddressDto;
import com.commerceApp.commerceApp.dtos.profileDtos.CustomerViewProfileDto;
import com.commerceApp.commerceApp.models.Customer;
import com.commerceApp.commerceApp.repositories.userRepos.CustomerRepository;
//import com.commerceApp.commerceApp.services.CurrentUserService;
import com.commerceApp.commerceApp.services.CurrentUserService;
import com.commerceApp.commerceApp.services.CustomerService;
import com.commerceApp.commerceApp.services.ImageService;
import com.commerceApp.commerceApp.services.UserService;
import com.commerceApp.commerceApp.util.responseDtos.BaseDto;
import com.commerceApp.commerceApp.util.responseDtos.ObjectDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;
import java.security.Principal;

@Api(value = "CustomerController", description = "REST APIs related to customer")
@RestController
public class CustomerController {
    @Autowired
    CustomerService customerService;
    @Autowired
    UserService userService;
    @Autowired
    ImageService imageService;
    @Autowired
    CurrentUserService currentUserService;
    @Autowired
    CustomerRepository customerRepository;


    @ApiOperation(value = "To get customer profile", authorizations = {@Authorization(value = "Bearer")})
    @GetMapping(value = "/customer/profile", produces = "application/json")
    public CustomerViewProfileDto getProfileDetails(@ApiIgnore HttpServletRequest request) {
        Principal principal = request.getUserPrincipal();
        String username = principal.getName();
        return customerService.getUserProfile(username);
    }

    @ApiOperation(value = "To upload customer pic", authorizations = {@Authorization(value = "Bearer")})
    @PostMapping("/customer/uploadProfilePic")
    public ObjectDto uploadFile(@RequestParam("file") MultipartFile file) throws IOException
    {
        String username = currentUserService.getUser();
        Customer customer = customerRepository.findByEmail(username);
        return imageService.uploadSingleImage(file,customer);

    }

    @ApiOperation(value = "To get customer pic", authorizations = {@Authorization(value = "Bearer")})
    @GetMapping("/customer/viewProfileImage")
    public ObjectDto viewProfileImage(HttpServletRequest request) throws IOException {
        String username = currentUserService.getUser();
        Customer customer = customerRepository.findByEmail(username);
        String filename = customer.getId().toString()+"_";
        return imageService.downloadImage(filename,request);
    }




    @ApiOperation(value = "To get customer's address", authorizations = {@Authorization(value = "Bearer")})
    @GetMapping(value = "/customer/addresses", produces = "application/json")

    public ResponseEntity<BaseDto> getCustomerAddresses(@ApiIgnore HttpServletRequest request) {
        Principal principal = request.getUserPrincipal();
        String username = principal.getName();
        return new ResponseEntity<>(customerService.getCustomerAddresses(username), HttpStatus.OK);
    }

    @ApiOperation(value = "To add customer's address", authorizations = {@Authorization(value = "Bearer")})
    @PostMapping(value = "/customer/addresses", produces = "application/json")
    public ResponseEntity<BaseDto> addNewAddress(@Valid @RequestBody AddressDto addressDto, HttpServletRequest request) {
        Principal principal = request.getUserPrincipal();
        String username = principal.getName();
        return new ResponseEntity<>(customerService.addNewAddress(username, addressDto), HttpStatus.OK);

    }


    @ApiOperation(value = "Update customer's profile", authorizations = {@Authorization(value = "Bearer")})
    @PatchMapping(value = "/customer/profile", produces = "application/json")
    public ResponseEntity updateProfile(@Valid @RequestBody CustomerViewProfileDto customerViewProfileDto, @ApiIgnore HttpServletRequest httpServletRequest) {
        Principal principal = httpServletRequest.getUserPrincipal();
        String username = principal.getName();
        return customerService.updateProfile(username, customerViewProfileDto);
    }

    @ApiOperation(value = "To delete customer's address", authorizations = {@Authorization(value = "Bearer")})
    @DeleteMapping(value = "/customer/addresses/{id}", produces = "application/json")
    public ResponseEntity<BaseDto> deleteAddressById(@PathVariable Long id, @ApiIgnore HttpServletRequest request) {
        Principal principal = request.getUserPrincipal();
        String username = principal.getName();
        return new ResponseEntity<>(customerService.deleteAddress(username, id), HttpStatus.OK);

    }

    @ApiOperation(value = "To update customer's address", authorizations = {@Authorization(value = "Bearer")})
    @PatchMapping(value = "/updateAddress/{id}", produces = "application/json")
    public ResponseEntity<String> updateCustomerAddress(@Valid @RequestBody AddressDto addressDto, @PathVariable Long id, @ApiIgnore HttpServletRequest httpServletRequest) {
        Principal principal = httpServletRequest.getUserPrincipal();
        String username = principal.getName();
        return userService.updateAddressById(username, id, addressDto);

    }


}
