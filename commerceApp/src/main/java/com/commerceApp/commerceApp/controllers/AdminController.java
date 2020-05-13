package com.commerceApp.commerceApp.controllers;

import com.commerceApp.commerceApp.dtos.AdminCustomerDto;
import com.commerceApp.commerceApp.dtos.AdminSellerDto;
import com.commerceApp.commerceApp.services.AdminUnlockingService;
import com.commerceApp.commerceApp.services.CustomerService;
import com.commerceApp.commerceApp.services.SellerService;
import com.commerceApp.commerceApp.services.UserService;
import com.commerceApp.commerceApp.util.responseDtos.BaseDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.util.Arrays;
import java.util.List;
@Api(value = "AdminController", description = "REST APIs related to admin")
@RestController
public class AdminController {
    @Autowired
    CustomerService customerService;
    @Autowired
    SellerService sellerService;
    @Autowired
    UserService userService;
    @Autowired
    AdminUnlockingService adminUnlockingService;

    @ApiOperation(value = "Get all customers", authorizations = { @Authorization(value="Bearer") })

    @GetMapping(value = "/customers",produces = "application/json")
    public ResponseEntity<BaseDto>getAllCustomers(@RequestParam(defaultValue = "0") String offset,
                                                  @RequestParam(defaultValue = "10") String size,
                                                  @RequestParam(defaultValue = "id") String sortByField,
                                                  @RequestParam(required = false) String email) {
        if (email != null)
            return  new ResponseEntity<BaseDto>((customerService.getCustomerByEmail(email)), HttpStatus.OK);
        return new ResponseEntity<BaseDto>(customerService.getAllCustomers(offset,size,sortByField),HttpStatus.OK);


    }
    @ApiOperation(value = "Get one customer", authorizations = { @Authorization(value="Bearer") })
    @GetMapping(value = "/customer",produces = "application/json")
    public ResponseEntity<BaseDto> getone(@RequestParam(required = true)String email){
       return new ResponseEntity<>(customerService.getCustomerByEmail(email),HttpStatus.OK);
    }
    @ApiOperation(value = "", authorizations = { @Authorization(value="Bearer") })
    @PutMapping(value = "/customers/{id}",produces = "application/json")
    public void UnlockuserAccount(@PathVariable Long id) {
        adminUnlockingService.UnlockingService();
    }

    @ApiOperation(value = "Get all sellers", authorizations = { @Authorization(value="Bearer") })
    @GetMapping(value = "/sellers",produces = "application/json")
    public ResponseEntity<BaseDto> getAllSellers(@RequestParam(defaultValue = "0") String offset,
                                              @RequestParam(defaultValue = "10") String size,
                                              @RequestParam(defaultValue = "id") String sortByField,
                                              @RequestParam(required = false) String email) {
        if (email != null)
            return new ResponseEntity<>(sellerService.getSellerByEmail(email),HttpStatus.OK);
        return new ResponseEntity<>(sellerService.getAllSeller(offset, size, sortByField),HttpStatus.OK);
    }

    @ApiOperation(value = "Activate user by id", authorizations = { @Authorization(value="Bearer") })
    @PutMapping(value = "/activate/{id}",produces = "application/json")
    public String activateUser(@PathVariable Long id, WebRequest webRequest) {
        return userService.activateUserById(id, webRequest);
    }

    @ApiOperation(value = "Deactivate user by id", authorizations = { @Authorization(value="Bearer") })
    @PutMapping(value = "/deactivate/{id}",produces = "application/json")
    public String deactivateUser(@PathVariable Long id, WebRequest request) {
        return userService.deactivateUserById(id, request);
    }

}
