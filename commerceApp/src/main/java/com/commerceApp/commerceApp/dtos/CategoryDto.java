package com.commerceApp.commerceApp.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CategoryDto {

    private Long id;
    private String name;
    private CategoryDto parent;
    public CategoryDto(){}

    public CategoryDto(Long id, String name, CategoryDto parent) {
        this.id = id;
        this.name = name;
        this.parent = parent;
    }
}
