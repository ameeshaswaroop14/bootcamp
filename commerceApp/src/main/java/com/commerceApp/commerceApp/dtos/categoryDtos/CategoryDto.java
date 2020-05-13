package com.commerceApp.commerceApp.dtos.categoryDtos;

import com.commerceApp.commerceApp.models.AuditInformation;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CategoryDto implements Serializable {
    private static final long serialVersionUID=1L;

    private Long id;
    private String name;
    private CategoryDto parent;
    public CategoryDto(){}

    public CategoryDto(Long id, String name, CategoryDto parent) {
        this.id = id;
        this.name = name;
        this.parent = parent;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CategoryDto getParent() {
        return parent;
    }

    public void setParent(CategoryDto parent) {
        this.parent = parent;
    }
}
