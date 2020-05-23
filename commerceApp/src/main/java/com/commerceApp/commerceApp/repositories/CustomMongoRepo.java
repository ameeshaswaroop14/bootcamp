package com.commerceApp.commerceApp.repositories;

import com.commerceApp.commerceApp.models.MongoInfo;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CustomMongoRepo extends MongoRepository<MongoInfo,String> {
}
