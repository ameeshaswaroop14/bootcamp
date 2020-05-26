package com.commerceApp.commerceApp.services;

import com.commerceApp.commerceApp.bootloader.Bootstrap;
import com.commerceApp.commerceApp.models.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class CurrentUserService
{
    public String getUser()
    {
        final Logger logger = LoggerFactory.getLogger(CurrentUserService.class);
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;
        User user = null;
        if (principal instanceof UserDetails)
        {
            username = ((UserDetails) principal).getUsername();
            logger.info(username);

        } else {
            username = principal.toString();
            logger.info(username);

        }
        return username;
    }
}


