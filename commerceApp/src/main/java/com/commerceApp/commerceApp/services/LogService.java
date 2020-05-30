package com.commerceApp.commerceApp.services;

import com.commerceApp.commerceApp.repositories.CustomMongoRepo;
import com.commerceApp.commerceApp.repositories.MongoInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LogService {
    @Autowired
    MongoInfoRepository mongoInfoRepository;
    @Autowired
    CustomMongoRepo customMongoRepo;

    public List getAllLogs(Optional<String> searchType, Optional<String> search) {

        String type = searchType.get();
        String searchParam = search.get();


        return customMongoRepo.findByUsername(searchParam);


    }

    public List getAllLogs() {

        return customMongoRepo.findAll();
    }
}
