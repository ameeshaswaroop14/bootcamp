package com.commerceApp.commerceApp.dtos.categoryDtos;

import java.util.List;

public class CategoryMetadataFieldPairDto {
    private Long categoryId;
    private List<CategoryMetadataFieldDto> fieldValues;

    public CategoryMetadataFieldPairDto(){}

    public CategoryMetadataFieldPairDto(Long categoryId, List<CategoryMetadataFieldDto> fieldValues) {
        this.categoryId = categoryId;
        this.fieldValues = fieldValues;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public List<CategoryMetadataFieldDto> getFieldValues() {
        return fieldValues;
    }

    public void setFieldValues(List<CategoryMetadataFieldDto> fieldValues) {
        this.fieldValues = fieldValues;
    }
}
