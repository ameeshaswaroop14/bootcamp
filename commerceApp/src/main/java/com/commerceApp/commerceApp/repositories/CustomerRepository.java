package com.commerceApp.commerceApp.repositories;

import com.commerceApp.commerceApp.models.Customer;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CustomerRepository extends CrudRepository<Customer,Long> {
    Customer findByEmail(String email);
    List<Customer>findAll();
    List<Customer> findAll(Pageable pageable);
}
