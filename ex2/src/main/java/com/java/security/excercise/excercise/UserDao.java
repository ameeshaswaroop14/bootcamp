package com.java.security.excercise.excercise;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Arrays;

@Repository
public class UserDao {
    @Autowired
    UserRepository userRepository;

    AppUser loadUserByUsername(String username) {
        User user = userRepository.findByUsername(username);
        System.out.println(user);
        if (username != null) {
            return new AppUser(user.getUsername(), user.getPassword(), Arrays.asList(new GrantAuthorityImpl(user.getRole())));
        } else {
            throw new RuntimeException();
        }

    }
}
