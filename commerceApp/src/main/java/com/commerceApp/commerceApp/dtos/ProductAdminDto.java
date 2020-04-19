package com.commerceApp.commerceApp.dtos;

public class ProductAdminDto {
    private CategoryDto categoryDto;

    public ProductAdminDto(){}

    public CategoryDto getCategoryDto() {
        return categoryDto;
    }

    public void setCategoryDto(CategoryDto categoryDto) {
        this.categoryDto = categoryDto;
    }
}
