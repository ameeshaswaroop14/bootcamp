package com.commerceApp.commerceApp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@SpringBootApplication
public class commerceApp {
    @Autowired
    private TokenStore tokenStore;

    @GetMapping("/doLogout")
    public String logout(HttpServletRequest request){
        String authHeader = request.getHeader("Authorization");
        if (authHeader != null) {
            String tokenValue = authHeader.replace("Bearer", "").trim();
            OAuth2AccessToken accessToken = tokenStore.readAccessToken(tokenValue);
            tokenStore.removeAccessToken(accessToken);
        }
        return "Logged out successfully";
    }

    @GetMapping("/")
    public String index(){
        return "index";
    }

    @GetMapping("/admin/home")
    public String adminHome(){
        return "Admin home";
    }

    @GetMapping("/user/home")
    public String userHome(){
        return "User home";
    }

    @GetMapping("/customer/home")
    public String customerHome(){
        return "Customer Home";
    }

    @GetMapping("/seller/home")
    public String sellerHome(){
        return "Seller home";
    }

    public static void main(String[] args) {
        SpringApplication.run(commerceApp.class, args);
    }

}
