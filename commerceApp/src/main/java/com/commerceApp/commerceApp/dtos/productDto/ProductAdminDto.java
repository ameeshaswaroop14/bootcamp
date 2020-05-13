package com.commerceApp.commerceApp.dtos.productDto;

import com.commerceApp.commerceApp.dtos.categoryDtos.CategoryDto;
import com.commerceApp.commerceApp.models.AuditInformation;

import java.io.Serializable;


public class ProductAdminDto implements Serializable {
    private static final long serialVersionUID=1L;
    private CategoryDto categoryDto;

    public ProductAdminDto(){}

    public CategoryDto getCategoryDto() {
        return categoryDto;
    }


    public void setCategoryDto(CategoryDto categoryDto) {
        this.categoryDto = categoryDto;
    }
}
