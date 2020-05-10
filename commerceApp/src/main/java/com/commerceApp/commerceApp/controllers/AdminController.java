package com.commerceApp.commerceApp.controllers;

import com.commerceApp.commerceApp.dtos.AdminCustomerDto;
import com.commerceApp.commerceApp.dtos.AdminSellerDto;
import com.commerceApp.commerceApp.services.AdminUnlockingService;
import com.commerceApp.commerceApp.services.CustomerService;
import com.commerceApp.commerceApp.services.SellerService;
import com.commerceApp.commerceApp.services.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
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

    @ApiOperation(value = "", authorizations = { @Authorization(value="Bearer") })

    @GetMapping(value = "/customers",produces = "application/json")
    public List<AdminCustomerDto> getAllCustomers(@RequestParam(defaultValue = "0") String offset,
                                                  @RequestParam(defaultValue = "10") String size,
                                                  @RequestParam(defaultValue = "id") String sortByField,
                                                  @RequestParam(required = false) String email) {
        if (email != null)
            return Arrays.asList(customerService.getCustomerByEmail(email));

        return customerService.getAllCustomers(offset, size, sortByField);
    }
    @ApiOperation(value = "", authorizations = { @Authorization(value="Bearer") })
    @GetMapping(value = "/customer",produces = "application/json")
    public AdminCustomerDto getone(@RequestParam(required = true)String email){
       return customerService.getCustomerByEmail(email);
    }
    @ApiOperation(value = "", authorizations = { @Authorization(value="Bearer") })
    @PutMapping(value = "/customers/{id}",produces = "application/json")
    public void UnlockuserAccount(@PathVariable Long id) {
        adminUnlockingService.UnlockingService();
    }

    @ApiOperation(value = "", authorizations = { @Authorization(value="Bearer") })
    @GetMapping(value = "/sellers",produces = "application/json")
    public List<AdminSellerDto> getAllSellers(@RequestParam(defaultValue = "0") String offset,
                                              @RequestParam(defaultValue = "10") String size,
                                              @RequestParam(defaultValue = "id") String sortByField,
                                              @RequestParam(required = false) String email) {
        if (email != null)
            return Arrays.asList(sellerService.getSellerByEmail(email));
        return sellerService.getAllSeller(offset, size, sortByField);
    }

    @ApiOperation(value = "", authorizations = { @Authorization(value="Bearer") })
    @PutMapping(value = "/activate/{id}",produces = "application/json")
    public String activateUser(@PathVariable Long id, WebRequest webRequest) {
        return userService.activateUserById(id, webRequest);
    }

    @ApiOperation(value = "", authorizations = { @Authorization(value="Bearer") })
    @PutMapping(value = "/deactivate/{id}",produces = "application/json")
    public String deactivateUser(@PathVariable Long id, WebRequest request) {
        return userService.deactivateUserById(id, request);
    }

}
