package com.commerceApp.commerceApp.repositories;

import com.commerceApp.commerceApp.Models.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer,Long> {
    Customer findByEmail(String email);
}
