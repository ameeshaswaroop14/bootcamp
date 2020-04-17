package com.commerceApp.commerceApp.services;

import com.commerceApp.commerceApp.Models.CategoryMetadataField;
import com.commerceApp.commerceApp.repositories.FieldRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {
    @Autowired
    FieldRepository fieldRepository;
    public ResponseEntity<String> addNewMetadaField(String field){
        CategoryMetadataField categoryMetadataField=fieldRepository.findByName(field);
        if(categoryMetadataField!=null)
            return new ResponseEntity<>("Metadata Field already exists", HttpStatus.BAD_REQUEST);
            categoryMetadataField.setName(field);
            fieldRepository.save(categoryMetadataField);
            return new ResponseEntity<>("New metadata field added",HttpStatus.OK);
    }

}
