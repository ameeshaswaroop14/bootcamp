package com.commerceApp.commerceApp.dtos.productDto;

import com.commerceApp.commerceApp.dtos.categoryDtos.CategoryDto;
import com.commerceApp.commerceApp.models.AuditInformation;

import java.util.Set;

public class ProductCustomerDto  {
    private CategoryDto categoryDto;

    public ProductCustomerDto(){}




    public CategoryDto getCategoryDto() {
        return categoryDto;
    }

    public void setCategoryDto(CategoryDto categoryDto) {
        this.categoryDto = categoryDto;
    }
    private Set<ProductvariationSellerDto> variations;

    public Set<ProductvariationSellerDto> getVariations() {
        return variations;
    }

    public void setVariations(Set<ProductvariationSellerDto> variations) {
        this.variations = variations;
    }
}
