package com.commerceApp.commerceApp.controllers;

import com.commerceApp.commerceApp.dtos.AddressDto;
import com.commerceApp.commerceApp.dtos.profileDtos.SellerViewProfileDto;
import com.commerceApp.commerceApp.services.SellerService;
import com.commerceApp.commerceApp.services.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.Principal;

@Api(value = "SellerController", description = "REST APIs related to seller")
@RestController
public class SellerController {
    @Autowired
    SellerService sellerService;

    @Autowired
    UserService userService;

    @ApiOperation("Seller home")
    @GetMapping(value = "/seller/home",produces = "application/json")
    public ResponseEntity getsellerHome() {
        return new ResponseEntity<>("Seller home", HttpStatus.OK);
    }


    @ApiOperation(value = "To get seller profile", authorizations = { @Authorization(value="Bearer") })
    @GetMapping(value = "/seller/profile",produces = "application/json")
    public SellerViewProfileDto getProfileDetails(@ApiIgnore HttpServletRequest request) {
        Principal principal = request.getUserPrincipal();
        String username = principal.getName();
        return sellerService.getUserProfile(username);
    }

    @ApiOperation(value = "To update seller profile", authorizations = { @Authorization(value="Bearer") })
    @PatchMapping(value = "/seller/profile",produces = "application/json")
    public ResponseEntity updateProfileDetails(@RequestBody SellerViewProfileDto profileDto,@ApiIgnore HttpServletRequest request) {
        Principal principal = request.getUserPrincipal();
        String username = principal.getName();
        return new ResponseEntity(sellerService.updateUserProfile(username,profileDto),HttpStatus.OK);

    }

    @ApiOperation(value = "To update seller's address", authorizations = { @Authorization(value="Bearer") })
    @PatchMapping(value = "/seller/addresses/{id}",produces = "application/json")
    public ResponseEntity<String> updateAddress(@Valid @RequestBody AddressDto addressDto, @PathVariable Long id,@ApiIgnore HttpServletRequest request) {
        Principal principal = request.getUserPrincipal();
        String username = principal.getName();
        return userService.updateAddressById(username, id, addressDto);
    }
}

