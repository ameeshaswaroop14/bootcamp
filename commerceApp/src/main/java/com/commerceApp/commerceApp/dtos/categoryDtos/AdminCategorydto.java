package com.commerceApp.commerceApp.dtos.categoryDtos;

import com.commerceApp.commerceApp.dtos.categoryDtos.CategoryDto;
import com.commerceApp.commerceApp.dtos.categoryDtos.CategoryMetadataFieldDto;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.util.Set;
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AdminCategorydto implements Serializable {
    private static final long serialVersionUID=1L;
    CategoryDto category;
    Set<CategoryDto> subCategories;
    Set<CategoryMetadataFieldDto> fieldValues;

    public CategoryDto getCategory() {
        return category;
    }

    public void setCategory(CategoryDto category) {
        this.category = category;
    }

    public Set<CategoryDto> getSubCategories() {
        return subCategories;
    }

    public void setSubCategories(Set<CategoryDto> subCategories) {
        this.subCategories = subCategories;
    }

    public Set<CategoryMetadataFieldDto> getFieldValues() {
        return fieldValues;
    }

    public void setFieldValues(Set<CategoryMetadataFieldDto> fieldValues) {
        this.fieldValues = fieldValues;
    }
}
