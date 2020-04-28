package com.commerceApp.commerceApp.controllers;

import com.commerceApp.commerceApp.services.CategoryMetadataFieldService;
import com.commerceApp.commerceApp.services.CategoryService;
import com.commerceApp.commerceApp.util.responseDtos.BaseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CategoryController {
    @Autowired
    CategoryMetadataFieldService categoryMetadataFieldService;

    @Autowired
    CategoryService categoryService;

    @PostMapping("/metadata-fields")
    public ResponseEntity<BaseDto> addMetaDataField(@RequestParam String fieldName) {
        return categoryMetadataFieldService.addNewMetadataField(fieldName);
    }

    @GetMapping("/metadata-fields")
    public ResponseEntity<List> getAllMetadataFields(@RequestParam(defaultValue = "0") String offset,
                                                       @RequestParam(defaultValue = "10") String size,
                                                       @RequestParam(defaultValue = "id") String sortByField,
                                                       @RequestParam(defaultValue = "ascending") String order,
                                                       @RequestParam(required = false) Long categoryId) {

            return categoryMetadataFieldService.getAllMetadataFields(offset, size, sortByField, order, categoryId);


    }

    @PostMapping("/categories")
    public ResponseEntity<BaseDto> addCategory(@RequestParam String categoryName,
                                               @RequestParam(required = false) Long parentId) {
        return categoryService.createNewCategory(categoryName, parentId);
    }

    @GetMapping("/category/{id}")
    public ResponseEntity<BaseDto> getCategoryDetails(@PathVariable(name = "id") Long categoryId) {
        return categoryService.getCategory(categoryId);
    }

    @GetMapping("/categories")
    public ResponseEntity<BaseDto> getAllCategories(@RequestParam(defaultValue = "0") String offset,
                                                   @RequestParam(defaultValue = "10") String size,
                                                   @RequestParam(defaultValue = "id") String sortByField)
                                               {

        return categoryService.getAllCategories(offset, size, sortByField);
    }

    @DeleteMapping("/category/{id}")
    public ResponseEntity<BaseDto> deleteCategory(@PathVariable Long id){
        return categoryService.deleteCategoryById(id);
    }

    @PutMapping("/category/{id}")
    public ResponseEntity<BaseDto> updateCategory(@PathVariable Long id, @RequestParam String name){
        return categoryService.updateCategory(id, name);
    }

    @GetMapping("/categories/seller")
    public ResponseEntity<BaseDto> getAllCategories(){
        return categoryService.getAllCategoriesForSeller();
    }

    @GetMapping("/categories/customer")
    public ResponseEntity<BaseDto> getAllCategories(@RequestParam(required = false) Long id){
        return categoryService.getAllCategoriesForCustomer(id);
    }







}