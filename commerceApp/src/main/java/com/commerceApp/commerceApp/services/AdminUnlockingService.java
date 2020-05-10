package com.commerceApp.commerceApp.services;

import com.commerceApp.commerceApp.models.User;
import com.commerceApp.commerceApp.repositories.userRepos.UserRepository;
import com.commerceApp.commerceApp.security.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminUnlockingService {
    @Autowired
    UserRepository userRepository;
    User user;
    AppUser appUser=new AppUser();
    public void UnlockingService() {


        }
    }

