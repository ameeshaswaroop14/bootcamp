package com.commerceApp.commerceApp.repositories;

import com.commerceApp.commerceApp.models.MongoInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CustomMongoRepo extends MongoRepository<MongoInfo,String> {
    List<MongoInfo>findByUsername(String username);
    Page<MongoInfo> findAll(Pageable pageable);
}
