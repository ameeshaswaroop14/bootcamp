package com.commerceApp.commerceApp.dtos.categoryDtos;

import java.util.Set;

public class CategoryAdminResponseDto {

    CategoryDto category;
    Set<CategoryDto> subCategories;
    Set<CategoryMetadataFieldDto> fieldValues;
    public CategoryAdminResponseDto(){}

    public CategoryAdminResponseDto(CategoryDto category, Set<CategoryDto> subCategories, Set<CategoryMetadataFieldDto> fieldValues) {
        this.category = category;
        this.subCategories = subCategories;
        this.fieldValues = fieldValues;
    }

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
