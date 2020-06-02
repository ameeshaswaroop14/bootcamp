package com.commerceApp.commerceApp.services;

import com.commerceApp.commerceApp.models.MongoInfo;
import com.commerceApp.commerceApp.repositories.CustomMongoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LogService {

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
    public Page<MongoInfo> getAllLogs(Optional<String>offset, Optional<String >size, Optional<String>sortByField, Optional<String> order) {
        String getOffset=offset.get();
        String getSize=size.get();
        String getSortBy=sortByField.get();
        String getOrder=order.get();
        Integer pageNo = Integer.parseInt(getOffset);
        Integer pageSize = Integer.parseInt(getSize);

        if (getOrder.equalsIgnoreCase("des")) {

            Pageable pageable = PageRequest.of(pageNo, pageSize);
            Page<MongoInfo> logs = customMongoRepo.findAll(pageable);
            return logs;

        }
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(Sort.Order.asc(getSortBy)));
        Page<MongoInfo> logs = customMongoRepo.findAll(pageable);
        return logs;

    }
}
