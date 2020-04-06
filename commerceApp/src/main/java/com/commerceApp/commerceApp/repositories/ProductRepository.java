package com.commerceApp.commerceApp.repositories;

import com.commerceApp.commerceApp.Models.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product,Long> {
}
