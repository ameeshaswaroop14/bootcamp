package com.commerceApp.commerceApp.repositories;

import com.commerceApp.commerceApp.Models.User;
import com.commerceApp.commerceApp.Models.VerificationToken;
import org.springframework.data.repository.CrudRepository;

public interface VerificationTokenRepository extends CrudRepository<VerificationToken,Long> {
    VerificationToken findByToken(String token);
    VerificationToken findByUser(User user);

}