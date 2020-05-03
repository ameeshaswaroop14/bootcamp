package com.commerceApp.commerceApp.controllers;


import com.commerceApp.commerceApp.dtos.ForgotPassword;
import com.commerceApp.commerceApp.services.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.Principal;
import java.util.Date;

@RestController
public class UserController {

    @Autowired
    UserService userService;
    @ApiOperation("User home")
    @GetMapping("/user/home")
    public ResponseEntity<String> userHome(){
        return new ResponseEntity<>("User Home", HttpStatus.OK);
    }

    @ApiOperation("Change password for user")
    @PutMapping("/change-password")
    public ResponseEntity changePassword(@Valid @RequestBody ForgotPassword forgotPassword, HttpServletRequest request){
        Principal principal = request.getUserPrincipal();
        String username = principal.getName();
        return userService.changePassword(username, forgotPassword);
    }


}