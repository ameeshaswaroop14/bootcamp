package com.commerceApp.commerceApp.controllers;

import com.commerceApp.commerceApp.services.LoginService;
import com.commerceApp.commerceApp.services.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Map;

@RestController
public class LoginLogOutController {
    @Autowired
    private LoginService login;
    @Autowired
    private TokenStore tokenStore;
    /*
    @PostMapping("/login")
    public Map login(WebRequest webRequest,
                     @RequestParam("username") String email,
                     @RequestParam("password") String password,
                     @RequestParam("client_id") String clientId,
                     @RequestParam("client_secret") String clientSecret) throws Exception {
        return login.login(webRequest, email, password, clientId, clientSecret);
    }

     */
}
