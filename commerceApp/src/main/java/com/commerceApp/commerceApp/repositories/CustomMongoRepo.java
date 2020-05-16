package com.commerceApp.commerceApp.repositories;

import com.commerceApp.commerceApp.CustomFilter;
import com.commerceApp.commerceApp.MongoInfo;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CustomMongoRepo extends MongoRepository<MongoInfo,String> {
}
