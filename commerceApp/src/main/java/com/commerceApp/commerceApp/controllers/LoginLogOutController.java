package com.commerceApp.commerceApp.controllers;

import com.commerceApp.commerceApp.services.LoginService;
import com.commerceApp.commerceApp.services.TokenService;
import com.commerceApp.commerceApp.services.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Date;
import java.util.Map;
@Api(value = "LoginLogOutController", description = "REST APIs related to Logout")
@RestController
public class LoginLogOutController {
    @Autowired
    private LoginService login;
    @Autowired
    private TokenStore tokenStore;
    @Autowired
    UserService userService;

   /* @PostMapping("/login")
    public Map login(WebRequest webRequest,
                     @RequestParam("username") String email,
                     @RequestParam("password") String password,
                     @RequestParam("client_id") String clientId,
                     @RequestParam("client_secret") String clientSecret) throws Exception {
        return login.login(webRequest, email, password, clientId, clientSecret);
    }

    */
   @ApiOperation(value = "To logout", authorizations = { @Authorization(value="Bearer") })
    @PostMapping(value = "/doLogout",produces = "application/json")
    public ResponseEntity<String> logout(@ApiIgnore HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        if (authHeader != null) {
            String tokenValue = authHeader.replace("Bearer", "").trim();
            OAuth2AccessToken accessToken = tokenStore.readAccessToken(tokenValue);
            tokenStore.removeAccessToken(accessToken);
        }
        return new ResponseEntity<>("Logged out", HttpStatus.OK);
    }






}
