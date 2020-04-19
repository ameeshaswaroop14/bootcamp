package com.commerceApp.commerceApp.controllers;

import com.commerceApp.commerceApp.services.CategoryMetadataFieldService;
import com.commerceApp.commerceApp.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CategoryController {
    @Autowired
    CategoryMetadataFieldService categoryMetadataFieldService;

    @Autowired
    CategoryService categoryService;
    @PostMapping("/metadata-fields")
    public ResponseEntity<String> addMetaDataField(@RequestParam String fieldName) {
        return categoryMetadataFieldService.addNewMetadaField(fieldName);
    }

    @PostMapping("/categories")
    public ResponseEntity<String> addCategory(@RequestParam String categoryName,
                                              @RequestParam(required = false) Long parentId) {
        return categoryService.createNewCategory(categoryName, parentId);
    }



}
