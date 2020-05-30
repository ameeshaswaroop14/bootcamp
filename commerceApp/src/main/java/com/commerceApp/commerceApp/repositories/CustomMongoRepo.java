package com.commerceApp.commerceApp.repositories;

import com.commerceApp.commerceApp.models.MongoInfo;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Date;
import java.util.List;

public interface CustomMongoRepo extends MongoRepository<MongoInfo,String> {
    List<MongoInfo>findByUsername(String username);
    List<MongoInfo>findByDate(String date);
}
