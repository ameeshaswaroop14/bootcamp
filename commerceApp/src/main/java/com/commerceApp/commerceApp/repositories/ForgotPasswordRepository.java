package com.commerceApp.commerceApp.repositories;

import com.commerceApp.commerceApp.Models.ForgotPasswordToken;
import com.commerceApp.commerceApp.Models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ForgotPasswordRepository extends CrudRepository<ForgotPasswordToken, Long> {

    ForgotPasswordToken findByToken(String token);

    ForgotPasswordToken findByUser(User user);
}
