package com.commerceApp.commerceApp.dtos;

import java.util.Map;
import java.util.Set;

public class CategoryFilteringDetailsDto {

    private Map<String, Set<String>> fieldValues;
    private Set<String> brands;
    private Double minPrice;
    private Double maxPrice;
    CategoryFilteringDetailsDto(){}

    public CategoryFilteringDetailsDto(Map<String, Set<String>> fieldValues, Set<String> brands, Double minPrice, Double maxPrice) {
        this.fieldValues = fieldValues;
        this.brands = brands;
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
    }

    public Map<String, Set<String>> getFieldValues() {
        return fieldValues;
    }

    public void setFieldValues(Map<String, Set<String>> fieldValues) {
        this.fieldValues = fieldValues;
    }

    public Set<String> getBrands() {
        return brands;
    }

    public void setBrands(Set<String> brands) {
        this.brands = brands;
    }

    public Double getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(Double minPrice) {
        this.minPrice = minPrice;
    }

    public Double getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(Double maxPrice) {
        this.maxPrice = maxPrice;
    }
}
