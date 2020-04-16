package com.commerceApp.commerceApp.services;

import com.commerceApp.commerceApp.Models.*;
import com.commerceApp.commerceApp.dtos.CustomerRegistrationDto;
import com.commerceApp.commerceApp.dtos.SellerRegistrationDto;
import com.commerceApp.commerceApp.exceptions.EmailAlreadyExistsException;
import com.commerceApp.commerceApp.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.WebRequest;

import java.util.*;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    MailService mailService;


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



}


