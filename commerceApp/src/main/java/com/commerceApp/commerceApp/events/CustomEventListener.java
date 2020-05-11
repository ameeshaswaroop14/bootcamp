package com.commerceApp.commerceApp.events;

import com.commerceApp.commerceApp.models.User;
import com.commerceApp.commerceApp.models.UserAttempts;
import com.commerceApp.commerceApp.repositories.UserAttemptsRepository;
import com.commerceApp.commerceApp.repositories.userRepos.UserRepository;
import com.commerceApp.commerceApp.services.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;

@Component
public class CustomEventListener {
    @Autowired
    UserAttemptsRepository userAttemptsRepository;

    @Autowired
    UserRepository userRepository;

    @Lazy
    @Autowired
    MailService mailService;

    @EventListener
    public void AuthenticationFailEvent(AuthenticationFailureBadCredentialsEvent event)
    {
        String username = event.getAuthentication().getPrincipal().toString();
        Iterable<UserAttempts> userAttempts = userAttemptsRepository.findAll();
        int count=0;
        for (UserAttempts userAttempts1 : userAttempts)
        {
            if (userAttempts1.getEmail().equals(username))
            {
                if (userAttempts1.getAttempts()>=2)
                {
                    User user = userRepository.findByEmail(username);
                    user.setActive(false);
                    user.setLocked(true);
                    userRepository.save(user);
                    count++;
                    mailService.sendAccountLockingMail(username);
                    System.out.println(user.getEmail());

                    throw new BadCredentialsException("Account locked");
                }
                else {
                    userAttempts1.setAttempts(userAttempts1.getAttempts() + 1);
                    userAttemptsRepository.save(userAttempts1);
                    count++;
                }
            }
        }
        if (count==0)
        {
            UserAttempts userAttempts1 = new UserAttempts();
            User user = userRepository.findByEmail(username);
            userAttempts1.setEmail(user.getEmail());
            userAttempts1.setAttempts(1);
            userAttemptsRepository.save(userAttempts1);
        }
    }  @EventListener
    public void AuthenticationPass(AuthenticationSuccessEvent event)
    {

        try {
            LinkedHashMap<String ,String > hashMap = (LinkedHashMap<String, String>) event.getAuthentication().getDetails();
            Iterable<UserAttempts> userAttempts = userAttemptsRepository.findAll();


            for (UserAttempts userAttempts1 : userAttempts)
            {
                if (userAttempts1.getEmail().equals(hashMap.get("username")))
                {
                    userAttemptsRepository.deleteById(userAttempts1.getId());
                }
            }
        }
        catch (Exception e)
        {

        }
    }
}

