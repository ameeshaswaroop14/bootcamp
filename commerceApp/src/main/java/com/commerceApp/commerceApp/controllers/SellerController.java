package com.commerceApp.commerceApp.controllers;

import com.commerceApp.commerceApp.dtos.AddressDto;
import com.commerceApp.commerceApp.dtos.profileDtos.SellerViewProfileDto;
import com.commerceApp.commerceApp.services.SellerService;
import com.commerceApp.commerceApp.services.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.Principal;


@RestController
public class SellerController {
    @Autowired
    SellerService sellerService;

    @Autowired
    UserService userService;

    @ApiOperation("Seller home")
    @GetMapping("/seller/home")
    public ResponseEntity getsellerHome() {
        return new ResponseEntity<>("Seller home", HttpStatus.OK);
    }

    @ApiOperation("Get seller profile details")
    @GetMapping("/seller/profile")
    public SellerViewProfileDto getProfileDetails(HttpServletRequest request) {
        Principal principal = request.getUserPrincipal();
        String username = principal.getName();
        return sellerService.getUserProfile(username);
    }

    @ApiOperation("Update seller profile details")
    @PatchMapping("/seller/profile")
    public ResponseEntity updateProfileDetails(@RequestBody SellerViewProfileDto profileDto, HttpServletRequest request) {
        Principal principal = request.getUserPrincipal();
        String username = principal.getName();
        return sellerService.updateUserProfile(username, profileDto);
    }

    @ApiOperation("update seller's address")
    @PatchMapping("/seller/addresses/{id}")
    public ResponseEntity<String> updateAddress(@Valid @RequestBody AddressDto addressDto, @PathVariable Long id, HttpServletRequest request) {
        Principal principal = request.getUserPrincipal();
        String username = principal.getName();
        return userService.updateAddressById(username, id, addressDto);
    }
}

