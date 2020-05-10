package com.commerceApp.commerceApp.repositories.tokenRepos;

import com.commerceApp.commerceApp.models.tokens.ForgotPasswordToken;
import com.commerceApp.commerceApp.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ForgotPasswordRepository extends CrudRepository<ForgotPasswordToken, Long> {

    ForgotPasswordToken findByToken(String token);

    ForgotPasswordToken findByUser(User user);
}
