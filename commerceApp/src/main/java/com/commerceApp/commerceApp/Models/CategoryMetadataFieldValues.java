package com.commerceApp.commerceApp.Models;

import javax.persistence.*;

@Entity
public class CategoryMetadataFieldValues {
    @EmbeddedId
    private CategoryMetadataFieldValuesId id = new CategoryMetadataFieldValuesId();

    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("categoryId")
    private Category category;

    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("categoryMetadataFieldId")
    private CategoryMetadataField categoryMetadataField;

    private String value;

    public CategoryMetadataFieldValues(String value)
    {
        this.value = value;
    }

    @Override
    public String toString() {
        return "CategoryMetadataFieldValues{" +
                "id=" + id +
                ", category=" + category.getName() +
                ", categoryMetadataField=" + categoryMetadataField.getName() +
                ", values='" + value + '\'' +
                '}';
    }
}


