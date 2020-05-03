package com.commerceApp.commerceApp.util;

import com.commerceApp.commerceApp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

public class AuditorAwareImpl implements AuditorAware<String> {

    @Autowired
    UserService userService;

    @Override
    public Optional<String> getCurrentAuditor() {

        Optional<String> currentUser = Optional.empty();
        String principal = userService.getCurrentLoggedInUser();
        currentUser = Optional.of(principal);
        return currentUser;
    }

}


