package com.commerceApp.commerceApp.security;

import com.commerceApp.commerceApp.Models.User;
import com.commerceApp.commerceApp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
@Service
public class UserDao {

    @Autowired
    UserRepository userRepository;

    AppUser loadUserByUsername(String email) {
        User user = userRepository.findByEmail(email);
        if (user != null) {
            return new AppUser(user);
        } else {
            throw new UsernameNotFoundException("user  " + user.getEmail() + " was not found");
        }
    }

}
