package com.commerceApp.commerceApp.repositories.userRepos;

import com.commerceApp.commerceApp.models.Customer;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CustomCustomerRepo {
    Customer findByEmail(String email);
    List<Customer> findAll();
    List<Customer> findAll(Pageable pageable);
}
