package com.commerceApp.commerceApp.controllers;

import com.commerceApp.commerceApp.dtos.categoryDtos.CategoryMetadataFieldValuesDto;
import com.commerceApp.commerceApp.services.CategoryMetadataFieldService;
import com.commerceApp.commerceApp.services.CategoryService;
import com.commerceApp.commerceApp.util.responseDtos.BaseDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.util.List;
@Api(value = "CategoryController", description = "REST APIs related to category")
@RestController
public class CategoryController {
    @Autowired
    CategoryMetadataFieldService categoryMetadataFieldService;

    @Autowired
    CategoryService categoryService;


    @ApiOperation(value = "To add metadata fields", authorizations = { @Authorization(value="Bearer") })
    @PostMapping(value = "/metadata-fields",produces = "application/json")
    public ResponseEntity<BaseDto> addMetaDataField(@RequestParam String fieldName, WebRequest webRequest) {
        return categoryMetadataFieldService.addNewMetadataField(fieldName, webRequest);
    }

    @ApiOperation(value = "To get all metadata fields", authorizations = { @Authorization(value="Bearer") })
    @GetMapping(value = "/metadata-fields",produces = "application/json")
    public ResponseEntity<List> getAllMetadataFields(@RequestParam(defaultValue = "0") String offset,
                                                     @RequestParam(defaultValue = "10") String size,
                                                     @RequestParam(defaultValue = "id") String sortByField,
                                                     @RequestParam(defaultValue = "ascending") String order,
                                                     @RequestParam(required = false) Long categoryId) {

        return categoryMetadataFieldService.getAllMetadataFields(offset, size, sortByField, order, categoryId);


    }

    @ApiOperation(value = "To add category", authorizations = { @Authorization(value="Bearer") })
    @PostMapping(value = "/categories",produces = "application/json")
    public ResponseEntity<BaseDto> addCategory(@RequestParam String categoryName,
                                               @RequestParam(required = false) Long parentId) {
        return categoryService.createNewCategory(categoryName, parentId);
    }

    @ApiOperation(value = "To get category details by id", authorizations = { @Authorization(value="Bearer") })
    @GetMapping(value = "/category/{id}",produces = "application/json")
    public ResponseEntity<BaseDto> getCategoryDetails(@PathVariable(name = "id") Long categoryId) {
        return categoryService.getCategory(categoryId);
    }

    @ApiOperation(value = "To get all categories", authorizations = { @Authorization(value="Bearer") })
    @GetMapping("/categories")
    public ResponseEntity<BaseDto> getAllCategories(@RequestParam(defaultValue = "0") String offset,
                                                    @RequestParam(defaultValue = "10") String size,
                                                    @RequestParam(defaultValue = "id") String sortByField) {

        return categoryService.getAllCategories(offset, size, sortByField);
    }

    @ApiOperation(value = "To delete category by id", authorizations = { @Authorization(value="Bearer") })
    @DeleteMapping(value = "/category/{id}",produces = "application/json")
    public ResponseEntity<BaseDto> deleteCategory(@PathVariable Long id) {
        return categoryService.deleteCategoryById(id);
    }


    @ApiOperation(value = "To update category by id", authorizations = { @Authorization(value="Bearer") })
    @PutMapping(value = "/category/{id}",produces = "application/json")
    public ResponseEntity<BaseDto> updateCategory(@PathVariable Long id, @RequestParam String name) {
        return categoryService.updateCategory(id, name);
    }

    @ApiOperation(value = "To get all categories ", authorizations = { @Authorization(value="Bearer") })
    @GetMapping(value = "/categories/seller",produces = "application/json")
    public ResponseEntity<BaseDto> getAllCategories() {
        return categoryService.getAllCategoriesForSeller();
    }

    @ApiOperation(value = "To get all categories for customer", authorizations = { @Authorization(value="Bearer") })
    @GetMapping(value = "/categories/customer",produces = "application/json")
    public ResponseEntity<BaseDto> getAllCategories(@RequestParam(required = false) Long id) {
        return categoryService.getAllCategoriesForCustomer(id);
    }


    @ApiOperation(value = "To add metadata fields values to category", authorizations = { @Authorization(value="Bearer") })
    @PostMapping(value = "/metadata-field-values",produces = "application/json")
    public ResponseEntity<BaseDto> addMetadataFieldValues(@RequestBody CategoryMetadataFieldValuesDto fieldValueDtos) {
        return categoryService.createCategoryMetadataFieldValues(fieldValueDtos);
    }
    @ApiOperation(value = "To get Filtering Details For Category", authorizations = { @Authorization(value="Bearer") })
    @GetMapping(value = "/category/filtering-details/{categoryId}",produces = "application/json")
    public ResponseEntity<BaseDto> getFilteringDetailsForCategory(@PathVariable Long categoryId) {
        return categoryService.getFilteringDetailsForCategory(categoryId);

    }
}