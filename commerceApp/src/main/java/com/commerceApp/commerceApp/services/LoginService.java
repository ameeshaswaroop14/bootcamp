package com.commerceApp.commerceApp.services;

import com.commerceApp.commerceApp.Models.User;
import com.commerceApp.commerceApp.repositories.UserRepository;
import jdk.internal.net.http.common.ImmutableExtendedSSLSession;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.*;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.WebRequest;

import java.util.Arrays;
import java.util.Locale;
import java.util.Map;

@Service
public class LoginService {
    @Autowired
    private DefaultTokenServices defaultTokenServices;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MessageSource messageSource;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private MailService mailService;
    @Autowired
    private AuthenticationManager authenticationManager;
    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public Map login(WebRequest webRequest, String email, String password, String clientId, String clientSecret) throws Exception
    {

        Locale locale = webRequest.getLocale();
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(email, password);
        Authentication authentication = this.authenticationManager.authenticate(authenticationToken);
        User user = (User) authentication.getPrincipal();



            return createToken(clientId, clientSecret, email, password);


    }

    public Map createToken(String clientId, String clientSecret, String email, String password) {


        RestTemplate restTemplate = new RestTemplate();

        // According OAuth documentation we need to send the client id and secret key in the header for authentication
        String credentials = clientId + ":" + clientSecret;
        String encodedCredentials = new String(Base64.encodeBase64(credentials.getBytes()));

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        HttpEntity<String> request = new HttpEntity<String>(headers);

        String access_token_url = "http://localhost:8080/oauth/token";
        access_token_url += "?grant_type=password&client_id=" + clientId + "&client_secret=" + clientSecret + "&password=" + password + "&username=" + email;

        ResponseEntity<Map> response = restTemplate.exchange(access_token_url, HttpMethod.POST, request, Map.class);

        System.out.println("Access Token Response ---------" + response.getBody());
        return response.getBody();

    }
}


