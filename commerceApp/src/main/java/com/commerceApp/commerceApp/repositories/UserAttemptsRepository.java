package com.commerceApp.commerceApp.repositories;


import com.commerceApp.commerceApp.models.UserAttempts;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAttemptsRepository extends CrudRepository<UserAttempts, Long> {
}