package com.commerceApp.commerceApp.controllers;

import com.commerceApp.commerceApp.dtos.categoryDtos.CategoryMetadataFieldValuesDto;
import com.commerceApp.commerceApp.services.CategoryMetadataFieldService;
import com.commerceApp.commerceApp.services.CategoryService;
import com.commerceApp.commerceApp.util.responseDtos.BaseDto;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.util.List;

@RestController
public class CategoryController {
    @Autowired
    CategoryMetadataFieldService categoryMetadataFieldService;

    @Autowired
    CategoryService categoryService;

    @ApiOperation("To add a metadata field")
    @PostMapping("/metadata-fields")
    public ResponseEntity<BaseDto> addMetaDataField(@RequestParam String fieldName, WebRequest webRequest) {
        return categoryMetadataFieldService.addNewMetadataField(fieldName, webRequest);
    }

    @ApiOperation("to get all metadata fields")
    @GetMapping("/metadata-fields")
    public ResponseEntity<List> getAllMetadataFields(@RequestParam(defaultValue = "0") String offset,
                                                     @RequestParam(defaultValue = "10") String size,
                                                     @RequestParam(defaultValue = "id") String sortByField,
                                                     @RequestParam(defaultValue = "ascending") String order,
                                                     @RequestParam(required = false) Long categoryId) {

        return categoryMetadataFieldService.getAllMetadataFields(offset, size, sortByField, order, categoryId);


    }

    @ApiOperation("To add categories")
    @PostMapping("/categories")
    public ResponseEntity<BaseDto> addCategory(@RequestParam String categoryName,
                                               @RequestParam(required = false) Long parentId) {
        return categoryService.createNewCategory(categoryName, parentId);
    }

    @ApiOperation("To get category details by id")
    @GetMapping("/category/{id}")
    public ResponseEntity<BaseDto> getCategoryDetails(@PathVariable(name = "id") Long categoryId) {
        return categoryService.getCategory(categoryId);
    }

    @ApiOperation("To get all categories")
    @GetMapping("/categories")
    public ResponseEntity<BaseDto> getAllCategories(@RequestParam(defaultValue = "0") String offset,
                                                    @RequestParam(defaultValue = "10") String size,
                                                    @RequestParam(defaultValue = "id") String sortByField) {

        return categoryService.getAllCategories(offset, size, sortByField);
    }

    @ApiOperation("To delete category by id")
    @DeleteMapping("/category/{id}")
    public ResponseEntity<BaseDto> deleteCategory(@PathVariable Long id) {
        return categoryService.deleteCategoryById(id);
    }

    @ApiOperation("To update category by id")
    @PutMapping("/category/{id}")
    public ResponseEntity<BaseDto> updateCategory(@PathVariable Long id, @RequestParam String name) {
        return categoryService.updateCategory(id, name);
    }

    @ApiOperation("To get all categories for seller")
    @GetMapping("/categories/seller")
    public ResponseEntity<BaseDto> getAllCategories() {
        return categoryService.getAllCategoriesForSeller();
    }

    @ApiOperation("To get all categories for customer")
    @GetMapping("/categories/customer")
    public ResponseEntity<BaseDto> getAllCategories(@RequestParam(required = false) Long id) {
        return categoryService.getAllCategoriesForCustomer(id);
    }

    @ApiOperation("To add metadata field values")
    @PostMapping("/metadata-field-values")
    public ResponseEntity<BaseDto> addMetadataFieldValues(@RequestBody CategoryMetadataFieldValuesDto fieldValueDtos) {
        return categoryService.createCategoryMetadataFieldValues(fieldValueDtos);
    }


}