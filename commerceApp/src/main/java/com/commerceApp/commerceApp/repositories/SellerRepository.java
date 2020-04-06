package com.commerceApp.commerceApp.repositories;

import com.commerceApp.commerceApp.Models.Seller;
import org.springframework.data.repository.CrudRepository;

public interface SellerRepository extends CrudRepository<Seller,Long> {
    Seller findByEmail(String email);
}
