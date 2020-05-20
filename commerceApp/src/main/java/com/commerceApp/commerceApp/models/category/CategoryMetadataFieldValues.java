package com.commerceApp.commerceApp.models.category;

import com.commerceApp.commerceApp.models.AuditInformation;
import org.hibernate.envers.Audited;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
@Audited
@Entity

public class CategoryMetadataFieldValues extends AuditInformation implements Serializable {
    @EmbeddedId
    private CategoryMetadataFieldValuesId id = new CategoryMetadataFieldValuesId();
    private static final long serialVersionUID = 1L;

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

    public CategoryMetadataFieldValues() {

    }

    public CategoryMetadataFieldValuesId getId() {
        return id;
    }

    public void setId(CategoryMetadataFieldValuesId id) {
        this.id = id;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public CategoryMetadataField getCategoryMetadataField() {
        return categoryMetadataField;
    }

    public void setCategoryMetadataField(CategoryMetadataField categoryMetadataField) {
        this.categoryMetadataField = categoryMetadataField;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
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


