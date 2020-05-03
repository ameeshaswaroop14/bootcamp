package com.commerceApp.commerceApp.services;

import com.commerceApp.commerceApp.models.User;
import com.commerceApp.commerceApp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminUnlockingService {
    @Autowired
    UserRepository userRepository;
    User user;

    public void UnlockingService() {
        if (user.getLocked() == true) {
            user.setLocked(false);
            user.setAccountNotLocked(true);
        }
    }
}
