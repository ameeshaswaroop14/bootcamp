package com.commerceApp.commerceApp.scheduler;

import com.commerceApp.commerceApp.models.User;
import com.commerceApp.commerceApp.repositories.userRepos.UserRepository;
import com.commerceApp.commerceApp.services.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

@Component
public class PasswordExpirationScheduler {
    @Autowired
    UserRepository userRepository;
    @Autowired
    MailService mailService;


    @Scheduled(cron = "0 0 0 * * ?")
    public void passwordExpired() {
        List<User> userList = userRepository.findAll();
        for (User user : userList) {
            LocalDate currentDate = LocalDate.now();
            if (user.getPasswordUpdatedDate() != null) {
                LocalDate updatedDate= user.getPasswordUpdatedDate().toInstant()
                        .atZone(ZoneId.systemDefault())
                        .toLocalDate();

                if (currentDate.equals(updatedDate.plusDays(10))) {
                   // user.setExpired(true);
                    System.out.println(updatedDate);
                    String subject = "Reminder of password expiration";
                    String text = "Hi, \\n As per terms your password has expired";
                    mailService.sendEmail(user.getEmail(), subject, text);
                    userRepository.save(user);
                }
            }
        }
    }
}
