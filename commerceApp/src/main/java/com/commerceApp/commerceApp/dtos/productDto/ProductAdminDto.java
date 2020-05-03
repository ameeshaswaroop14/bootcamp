package com.commerceApp.commerceApp.dtos.productDto;

import com.commerceApp.commerceApp.dtos.categoryDtos.CategoryDto;
import com.commerceApp.commerceApp.models.AuditInformation;



public class ProductAdminDto{
    private CategoryDto categoryDto;

    public ProductAdminDto(){}

    public CategoryDto getCategoryDto() {
        return categoryDto;
    }


    public void setCategoryDto(CategoryDto categoryDto) {
        this.categoryDto = categoryDto;
    }
}
