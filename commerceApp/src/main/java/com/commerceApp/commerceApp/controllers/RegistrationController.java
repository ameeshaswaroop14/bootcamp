package com.commerceApp.commerceApp.controllers;

import com.commerceApp.commerceApp.Models.Customer;
import com.commerceApp.commerceApp.Models.Seller;
import com.commerceApp.commerceApp.dtos.CustomerRegistrationDto;
import com.commerceApp.commerceApp.dtos.SellerRegistrationDto;
import com.commerceApp.commerceApp.exceptions.EmailAlreadyExistsException;
import com.commerceApp.commerceApp.repositories.CustomerRepository;
import com.commerceApp.commerceApp.repositories.SellerRepository;
import com.commerceApp.commerceApp.services.CustomerService;
import com.commerceApp.commerceApp.services.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import javax.validation.Valid;

@RestController
public class RegistrationController {
    @Autowired
    CustomerService customerService;
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    SellerService sellerService;
    @Autowired
    SellerRepository sellerRepository;

    @PostMapping("/register/customer")
    public String registerCustomer(@Valid @RequestBody CustomerRegistrationDto customerRegistrationDto, WebRequest request){

        Customer customer = customerRepository.findByEmail(customerRegistrationDto.getEmail());

        if(customer != null)
            throw new EmailAlreadyExistsException("email id already exists");

        else{
            Customer newCustomer = customerService.toCustomer(customerRegistrationDto);
            Customer savedCustomer = customerRepository.save(newCustomer);
            System.out.println("customer registered successfully.");

            return "success";
        }
    }

    @PostMapping("/register/seller")
    public String registerSeller(@Valid @RequestBody SellerRegistrationDto sellerRegistrationDto, WebRequest webRequest){

        Seller seller = sellerRepository.findByEmail(sellerRegistrationDto.getEmail());

        if(seller != null)
            throw new EmailAlreadyExistsException("email id already exists");

        else{
            Seller newSeller = sellerService.toSeller(sellerRegistrationDto);
            Seller savedSeller = sellerRepository.save(newSeller);
            System.out.println("seller registered successfully.");

            return "success";
        }
    }

}
