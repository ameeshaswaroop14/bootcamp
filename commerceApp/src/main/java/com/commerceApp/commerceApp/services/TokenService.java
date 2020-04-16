package com.commerceApp.commerceApp.services;

import com.commerceApp.commerceApp.Models.ForgotPasswordToken;
import com.commerceApp.commerceApp.Models.User;
import com.commerceApp.commerceApp.Models.VerificationToken;
import com.commerceApp.commerceApp.repositories.ForgotPasswordRepository;
import com.commerceApp.commerceApp.repositories.UserRepository;
import com.commerceApp.commerceApp.repositories.VerificationTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TokenService {
    @Autowired
    VerificationTokenRepository verificationTokenRepository;
    @Autowired
    ForgotPasswordRepository forgotPasswordRepository;
    @Autowired
    UserRepository userRepository;
    public String createVerficationToken(User user){
        String token = UUID.randomUUID().toString();
        VerificationToken verificationToken = new VerificationToken(token, user);
        verificationTokenRepository.save(verificationToken);
        return token;
    }
    public void deleteVerificationToken(String token){
        VerificationToken verificationToken=verificationTokenRepository.findByToken(token);
        verificationTokenRepository.delete(verificationToken);
    }
    public VerificationToken getVerificationToken(String VerificationToken) {
        return verificationTokenRepository.findByToken(VerificationToken);
    }

    public String createForgotPasswordToken(User user){
        String token = UUID.randomUUID().toString();
        ForgotPasswordToken forgotPasswordToken = new ForgotPasswordToken(token, user);
        forgotPasswordRepository.save(forgotPasswordToken);
        return token;
    }

    public ForgotPasswordToken getForgotPasswordToken(String forgotPasswordToken) {
        return forgotPasswordRepository.findByToken(forgotPasswordToken);
    }

    public void deleteForgotToken(String token){
        ForgotPasswordToken forgotPasswordToken=forgotPasswordRepository.findByToken(token);
        forgotPasswordRepository.delete(forgotPasswordToken);
    }
    public ForgotPasswordToken getForgotPasswordToken(User user) {
        return forgotPasswordRepository.findByUser(user);
    }

    public void saveRegisteredUser(User user) {
        userRepository.save(user);
    }

}
