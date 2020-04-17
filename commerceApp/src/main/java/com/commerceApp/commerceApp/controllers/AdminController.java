package com.commerceApp.commerceApp.controllers;

import com.commerceApp.commerceApp.dtos.AdminCustomerDto;
import com.commerceApp.commerceApp.dtos.AdminSellerDto;
import com.commerceApp.commerceApp.repositories.CustomerRepository;
import com.commerceApp.commerceApp.services.CustomerService;
import com.commerceApp.commerceApp.services.SellerService;
import com.commerceApp.commerceApp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.util.Arrays;
import java.util.List;

@RestController
public class AdminController {
    @Autowired
    CustomerService customerService;
    @Autowired
    SellerService sellerService;
    @Autowired
    UserService userService;



   @GetMapping("/customers")
    public List<AdminCustomerDto> getAllCustomers(@RequestParam(defaultValue = "0") String offset,
                                                     @RequestParam(defaultValue = "10") String size,
                                                     @RequestParam(defaultValue = "id") String sortByField,
                                                     @RequestParam(required = false) String email){
        if(email!=null)
            return Arrays.asList(customerService.getCustomerByEmail(email));

        return customerService.getAllCustomers(offset, size, sortByField);
    }

    @GetMapping("/sellers")
    public  List<AdminSellerDto> getAllSellers(@RequestParam(defaultValue="0")String offset,
                                               @RequestParam(defaultValue = "10") String size,
                                               @RequestParam(defaultValue = "id") String sortByField,
                                               @RequestParam(required = false) String email){
       if(email!=null)
           return Arrays.asList(sellerService.getSellerByEmail(email));
       return sellerService.getAllSeller(offset,size,sortByField);
    }

    @PutMapping("/activate/{id}")
    public String activateUser(@PathVariable Long id, WebRequest webRequest){
        return userService.activateUserById(id, webRequest);
    }


    @PutMapping("/deactivate/{id}")
    public String deactivateUser(@PathVariable Long id, WebRequest request){
        return userService.deactivateUserById(id, request);
    }

}
