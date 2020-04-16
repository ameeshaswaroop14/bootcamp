package com.commerceApp.commerceApp.services;

import com.commerceApp.commerceApp.Models.Customer;
import com.commerceApp.commerceApp.Models.Role;
import com.commerceApp.commerceApp.Models.Seller;
import com.commerceApp.commerceApp.Models.User;
import com.commerceApp.commerceApp.dtos.CustomerRegistrationDto;
import com.commerceApp.commerceApp.dtos.SellerRegistrationDto;
import com.commerceApp.commerceApp.exceptions.EmailAlreadyExistsException;
import com.commerceApp.commerceApp.repositories.CustomerRepository;
import com.commerceApp.commerceApp.repositories.SellerRepository;
import com.commerceApp.commerceApp.repositories.UserRepository;
import com.commerceApp.commerceApp.repositories.roleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.WebRequest;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class RegistrationService {
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    CustomerService customerService;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    TokenService tokenService;
    @Autowired
    MailService mailService;
    @Autowired
    SellerService sellerService;
    @Autowired
    SellerRepository sellerRepository;
    @Autowired
    ActivationService activationService;
    @Autowired
    roleRepository repository;

    public String registerCustomer(CustomerRegistrationDto customerRegistrationDto, WebRequest webRequest) {
        Customer customer = customerRepository.findByEmail(customerRegistrationDto.getEmail());

        if (customer != null)
            throw new EmailAlreadyExistsException("email id already exists");

        else {
            Customer newCustomer = customerService.toCustomer(customerRegistrationDto);
            newCustomer.setPassword(passwordEncoder.encode(newCustomer.getPassword()));
            Set<Role> roleSet = new HashSet<>();
            roleSet.add(repository.findByAuthority("ROLE_CUSTOMER"));
            newCustomer.setRoles(roleSet);
            Customer savedCustomer = customerRepository.save(newCustomer);
            String subject = "Account Registration.";
            String appUrl = webRequest.getContextPath();
            activationService.sendActivationLinkMail(appUrl,savedCustomer,subject);
            String message = "Your account has been created, an activation link has been sent to your given email id.";
            return message;

        }
    }


    public String registerSeller(SellerRegistrationDto sellerRegistrationDto){
        String message=sellerService.checkIfUnique(sellerRegistrationDto);
        if(!(message=="unique")){
            return "Invalid data";
        }
        Seller seller=sellerService.toSeller(sellerRegistrationDto);
        seller.setPassword(passwordEncoder.encode(seller.getPassword()));
        sellerRepository.save(seller);
        sellerService.acknowledgementEmail(seller.getEmail());
        return  "Account created successfully. It will be activated after verification.";

    }

}
