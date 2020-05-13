package com.commerceApp.commerceApp.dtos.categoryDtos;

import java.io.Serializable;
import java.util.List;

public class CategoryMetadataFieldValuesDto implements Serializable {
    private static final long serialVersionUID=1L;
    private Long categoryId;
    private List<CategoryMetadataFieldDto> fieldValues;

    public CategoryMetadataFieldValuesDto(){}

    public CategoryMetadataFieldValuesDto(Long categoryId, List<CategoryMetadataFieldDto> fieldValues) {
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
