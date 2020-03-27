package com.commerceApp.commerceApp.repos;

import com.commerceApp.commerceApp.Models.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User,Integer> {

}
