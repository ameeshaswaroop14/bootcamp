package com.commerceApp.commerceApp.repositories;

import com.commerceApp.commerceApp.models.Seller;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SellerRepository extends CrudRepository<Seller,Long> {
    Seller findByEmail(String email);
    Seller findByCompanyName(String companyName);
    Seller findByGST(String GST);
    List<Seller> findAll();
    List<Seller> findAll(Pageable pageable);


}
