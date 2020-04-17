package com.commerceApp.commerceApp.Models;

import javax.persistence.*;
import java.util.Set;

@Entity
public class CategoryMetadataField {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    @OneToMany(mappedBy = "categoryMetadataField", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<CategoryMetadataFieldValues> fieldValues;

    public CategoryMetadataField( String name) {

        this.name = name;
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

    public Set<CategoryMetadataFieldValues> getFieldValues() {
        return fieldValues;
    }

    public void setFieldValues(Set<CategoryMetadataFieldValues> fieldValues) {
        this.fieldValues = fieldValues;
    }

    @Override
    public String toString() {
        return "CategoryMetadataField{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", fieldValues=" + fieldValues +
                '}';
    }
}
