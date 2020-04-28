package com.commerceApp.commerceApp.services;

import com.commerceApp.commerceApp.models.Customer;
import com.commerceApp.commerceApp.models.Role;
import com.commerceApp.commerceApp.models.Seller;
import com.commerceApp.commerceApp.dtos.registrationDtos.CustomerRegistrationDto;
import com.commerceApp.commerceApp.dtos.registrationDtos.SellerRegistrationDto;
import com.commerceApp.commerceApp.exceptions.EmailAlreadyExistsException;
import com.commerceApp.commerceApp.repositories.CustomerRepository;
import com.commerceApp.commerceApp.repositories.SellerRepository;
import com.commerceApp.commerceApp.repositories.roleRepository;
import com.commerceApp.commerceApp.validators.SellerValidations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.WebRequest;

import java.util.HashSet;
import java.util.Set;

import static com.commerceApp.commerceApp.util.EntityDtoMapping.toCustomer;
import static com.commerceApp.commerceApp.util.EntityDtoMapping.toSeller;

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
            Customer newCustomer = toCustomer(customerRegistrationDto);
            newCustomer.setPassword(passwordEncoder.encode(newCustomer.getPassword()));
            Set<Role> roleSet = new HashSet<>();
            roleSet.add(repository.findByAuthority("ROLE_CUSTOMER"));
            newCustomer.setRoles(roleSet);
            Customer savedCustomer = customerRepository.save(newCustomer);
            String subject = "Account Registration.";
            String appUrl = webRequest.getContextPath();
            mailService.sendActivationLinkMail(appUrl,savedCustomer,subject);
            String message = "Your account has been created, an activation link has been sent to your given email id.";
            return message;

        }
    }
    private SellerValidations sellerValidations;

    public String registerSeller(SellerRegistrationDto sellerRegistrationDto){
        String message= sellerValidations.checkIfUnique(sellerRegistrationDto);
        if(!(message=="unique")){
            return "Invalid data";
        }
        Seller seller=toSeller(sellerRegistrationDto);
        seller.setPassword(passwordEncoder.encode(seller.getPassword()));
        sellerRepository.save(seller);
        mailService.acknowledgementEmail(seller.getEmail());
        return  "Account created successfully. It will be activated after verification.";

    }

}
