package com.commerceApp.commerceApp.repositories;

import com.commerceApp.commerceApp.Models.User;
import org.springframework.data.repository.CrudRepository;


public interface UserRepository extends CrudRepository<User, Long> {

    User findByEmail(String email);

    User findByPassword(String password);
}
