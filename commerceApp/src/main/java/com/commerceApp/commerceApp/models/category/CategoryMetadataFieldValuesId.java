package com.commerceApp.commerceApp.models.category;

import org.hibernate.envers.Audited;

import javax.persistence.Embeddable;
import java.io.Serializable;
@Audited
@Embeddable
public class CategoryMetadataFieldValuesId implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long categoryId;
    private Long categoryMetadataFieldId;

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Long getCategoryMetadataFieldId() {
        return categoryMetadataFieldId;
    }

    public void setCategoryMetadataFieldId(Long categoryMetadataFieldId) {
        this.categoryMetadataFieldId = categoryMetadataFieldId;
    }

}