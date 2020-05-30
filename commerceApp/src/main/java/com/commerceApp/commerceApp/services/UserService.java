package com.commerceApp.commerceApp.services;

import com.commerceApp.commerceApp.models.*;
import com.commerceApp.commerceApp.dtos.AddressDto;
import com.commerceApp.commerceApp.dtos.ForgotPassword;
import com.commerceApp.commerceApp.repositories.*;
import com.commerceApp.commerceApp.repositories.userRepos.CustomUserRepo;
import com.commerceApp.commerceApp.repositories.userRepos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Transactional
@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    MailService mailService;
    @Autowired
    AddressRepository addressRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    ForgotPasswordService forgotPasswordService;
    @Autowired
    AddressRepositoryCustom addressRepositoryCustom;
    @Autowired
    CustomUserRepo customUserRepo;


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


    public ResponseEntity<String> updateAddressById(String email, Long addressId, AddressDto addressDto) {
        Optional<Address> address = Optional.ofNullable(addressRepositoryCustom.findAdressById(addressId));
        User user = userRepository.findByEmail(email);

        if (!address.isPresent()) {
            return new ResponseEntity<>("No address found with the given id;", HttpStatus.NOT_FOUND);
        }
        Address savedAddress = address.get();
        if (!savedAddress.getUser().getEmail().equals(email)) {
            return new ResponseEntity<>("Invalid Operation", HttpStatus.CONFLICT);
        }

        if (addressDto.getAddressLine() != null)
            savedAddress.setAddressLine(addressDto.getAddressLine());

        if (addressDto.getCity() != null)
            savedAddress.setCity(addressDto.getCity());

        if (addressDto.getState() != null)
            savedAddress.setState(addressDto.getState());

        if (addressDto.getCountry() != null)
            savedAddress.setCountry(addressDto.getCountry());

        if (addressDto.getZipCode() != null)
            savedAddress.setZipCode(addressDto.getZipCode());

        if (addressDto.getLabel() != null)
            savedAddress.setLabel(addressDto.getLabel());

        return new ResponseEntity<>("Address Updated", HttpStatus.OK);
    }

    public ResponseEntity<String> changePassword(String email, ForgotPassword passwords) {
        User user = userRepository.findByEmail(email);
        user.setPassword(passwordEncoder.encode(passwords.getPassword()));
        user.setPasswordUpdatedDate(new Date());
        userRepository.save(user);
        mailService.sendPasswordResetConfirmationMail(email);
        return new ResponseEntity<>("Password Changed", HttpStatus.OK);
    }

    public String getCurrentLoggedInUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }
        return username;
    }


    public List getAllUsers(Optional<String>offset,Optional<String >size, Optional<String>sortByField,Optional<String> order) {
        String getOffset=offset.get();
        String getSize=size.get();
        String getSortBy=sortByField.get();
        String getOrder=order.get();
        Integer pageNo = Integer.parseInt(getOffset);
        Integer pageSize = Integer.parseInt(getSize);

        if (getOrder.equalsIgnoreCase("des")) {

            Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(Sort.Order.desc(getSortBy)));
            List<User> users = userRepository.findAll(pageable);
            return users;

        }
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(Sort.Order.asc(getSortBy)));
        List<User> users = userRepository.findAll(pageable);
        return users;

    }

    public List getAllUsers(Optional<String> searchType, Optional<String> search) {

        String type=searchType.get();
        String searchParam=search.get();
        if (type.equalsIgnoreCase("email"))
            return customUserRepo.findByEmail(searchParam);

        else if (type.equalsIgnoreCase("firstName"))
            return customUserRepo.findByFirstName(searchParam);
        else if (type.equalsIgnoreCase("id"))
            return customUserRepo.findById(Long.valueOf(searchParam));
        else
            return customUserRepo.findByLastName(searchParam);

    }
    public List getAllUser(){

       return userRepository.findAll();
    }


}













