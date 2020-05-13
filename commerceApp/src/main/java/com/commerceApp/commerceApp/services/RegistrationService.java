package com.commerceApp.commerceApp.services;

import com.commerceApp.commerceApp.models.Customer;
import com.commerceApp.commerceApp.models.Role;
import com.commerceApp.commerceApp.models.Seller;
import com.commerceApp.commerceApp.dtos.registrationDtos.CustomerRegistrationDto;
import com.commerceApp.commerceApp.dtos.registrationDtos.SellerRegistrationDto;
import com.commerceApp.commerceApp.exceptions.EmailAlreadyExistsException;
import com.commerceApp.commerceApp.repositories.userRepos.CustomerRepository;
import com.commerceApp.commerceApp.repositories.userRepos.SellerRepository;
import com.commerceApp.commerceApp.repositories.roleRepository;
import com.commerceApp.commerceApp.util.responseDtos.BaseDto;
import com.commerceApp.commerceApp.util.responseDtos.ErrorDto;
import com.commerceApp.commerceApp.util.responseDtos.ResponseDto;
import com.commerceApp.commerceApp.validators.SellerValidations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.WebRequest;

import java.util.HashSet;
import java.util.Locale;
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
    MessageSource messageSource;
    @Autowired
    roleRepository repository;

    public BaseDto registerCustomer(CustomerRegistrationDto customerRegistrationDto, WebRequest webRequest) {
        Customer customer = customerRepository.findByEmail(customerRegistrationDto.getEmail());
        String message;

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
            Locale locale=webRequest.getLocale();
            mailService.sendActivationLinkMail(appUrl,savedCustomer,subject,webRequest.getLocale());
           // String message = "Your account has been created, an activation link has been sent to your given email id.";
           // BaseDto response = new ResponseDto<>(message,null);
            //return new ResponseEntity<>(response, HttpStatus.CREATED);
            BaseDto response;
            message = messageSource.getMessage("message.activationtoken", null, locale);
            response = new ResponseDto<>(message, null);
            return response;

        }
    }
    private SellerValidations sellerValidations;

    public BaseDto registerSeller(SellerRegistrationDto sellerRegistrationDto){
        String message= sellerValidations.checkIfUnique(sellerRegistrationDto);
        if(!message.equals("unique")){
            BaseDto response =new ErrorDto("Validation failed","Invalid data entered");
            return response;
        }
        Seller seller=toSeller(sellerRegistrationDto);
        seller.setPassword(passwordEncoder.encode(seller.getPassword()));
        Set<Role> roleSet = new HashSet<>();
        roleSet.add(repository.findByAuthority("ROLE_SELLER"));
        seller.setRoles(roleSet);
        sellerRepository.save(seller);
        mailService.acknowledgementEmail(seller.getEmail());
        BaseDto response=new ResponseDto<>("Account created successfully. It will be activated after verification.",null);
        return response;

    }
    


}
