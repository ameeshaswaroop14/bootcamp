package com.commerceApp.commerceApp.services;

import com.commerceApp.commerceApp.models.*;
import com.commerceApp.commerceApp.dtos.AddressDto;
import com.commerceApp.commerceApp.dtos.ForgotPassword;
import com.commerceApp.commerceApp.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.WebRequest;

import java.util.*;
@Transactional
@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    MailService mailService;
    @Autowired AddressRepository addressRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    ForgotPasswordService forgotPasswordService;


    public String activateUserById(Long id, WebRequest request) {
        Optional<User> user = userRepository.findById(id);
        String message;

        if (!user.isPresent()) {
            message = "No user found with this Id";
        } else {
            User savedUser = user.get();
            if (savedUser.isActive()) {
                message = "User already active";
            } else {
                savedUser.setActive(true);
                userRepository.save(savedUser);
                String subject = "Account Activation";
                String text = "Your account has been activated successfully by our team.";
                mailService.sendEmail(savedUser.getEmail(), subject, text);

                message = "User has been activated";
            }

        }
        return message;
    }

    public String deactivateUserById(Long id, WebRequest request) {
        Optional<User> user = userRepository.findById(id);
        String message;

        if (!user.isPresent()) {
            message = "No user found with this Id";

        } else {
            User savedUser = user.get();
            if (!savedUser.isActive()) {
                message = "User already inactive";
            } else {
                savedUser.setActive(false);
                userRepository.save(savedUser);
                String text = "your account has been de-activated.";
                String subject = "Account De-activation";
                mailService.sendEmail(savedUser.getEmail(), subject, text);

                message = "User has been deactivated.";
            }

        }
        return message;
    }

    boolean emailExist(String email) {
        User user = userRepository.findByEmail(email);
        if (user != null)
            return true;
        else
            return false;
    }

    boolean passwordExist(String password) {
        User user = userRepository.findByPassword(password);
        if (user != null)
            return true;
        else
            return false;
    }





    public ResponseEntity<String>updateAddressById(String email, Long addressId, AddressDto addressDto) {
        Optional<Address> address = addressRepository.findById(addressId);
        User user = userRepository.findByEmail(email);

        if(!address.isPresent()){
            return new ResponseEntity<>("No address found with the given id;", HttpStatus.NOT_FOUND);
        }
        Address savedAddress = address.get();
        if(!savedAddress.getUser().getEmail().equals(email)){
            return new ResponseEntity<>("Invalid Operation", HttpStatus.CONFLICT);
        }

        if(addressDto.getAddressLine() != null)
            savedAddress.setAddressLine(addressDto.getAddressLine());

        if(addressDto.getCity() != null)
            savedAddress.setCity(addressDto.getCity());

        if(addressDto.getState() != null)
            savedAddress.setState(addressDto.getState());

        if(addressDto.getCountry() != null)
            savedAddress.setCountry(addressDto.getCountry());

        if(addressDto.getZipCode() != null)
            savedAddress.setZipCode(addressDto.getZipCode());

        if(addressDto.getLabel() != null)
            savedAddress.setLabel(addressDto.getLabel());

        return new ResponseEntity<>("Address Updated", HttpStatus.OK);
    }

    public ResponseEntity<String> changePassword(String email, ForgotPassword passwords){
        User user = userRepository.findByEmail(email);
        user.setPassword(passwordEncoder.encode(passwords.getPassword()));
        userRepository.save(user);
        mailService.sendPasswordResetConfirmationMail(email);
        return new ResponseEntity<>("Password Changed", HttpStatus.OK);
    }
    public String getCurrentLoggedInUser()
    {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;
        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        }
        else {
            username = principal.toString();
        }
        return username;
    }



}


