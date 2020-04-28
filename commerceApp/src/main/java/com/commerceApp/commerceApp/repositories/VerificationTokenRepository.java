package com.commerceApp.commerceApp.repositories;

import com.commerceApp.commerceApp.models.User;
import com.commerceApp.commerceApp.models.tokens.VerificationToken;
import org.springframework.data.repository.CrudRepository;

public interface VerificationTokenRepository extends CrudRepository<VerificationToken,Long> {
    VerificationToken findByToken(String token);
    VerificationToken findByUser(User user);

}
