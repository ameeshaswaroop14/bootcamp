package com.commerceApp.commerceApp.dtos;

public class ProductCustomerDto {
    private CategoryDto categoryDto;

    public ProductCustomerDto(){}


    public CategoryDto getCategoryDto() {
        return categoryDto;
    }

    public void setCategoryDto(CategoryDto categoryDto) {
        this.categoryDto = categoryDto;
    }
}
