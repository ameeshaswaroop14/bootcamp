package com.commerceApp.commerceApp.dtos.productDto;

import java.io.Serializable;

public class ProductUpdateDto implements Serializable {
    private static final long serialVersionUID=1L;
    private String name;
    private String description;
    private Boolean isReturnable = false;
    private Boolean isCancelleable = false;

    public ProductUpdateDto(String name, String description, Boolean isReturnable, Boolean isCancelleable) {
        this.name = name;
        this.description = description;
        this.isReturnable = isReturnable;
        this.isCancelleable = isCancelleable;
    }
    public ProductUpdateDto(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getReturnable() {
        return isReturnable;
    }

    public void setReturnable(Boolean returnable) {
        isReturnable = returnable;
    }

    public Boolean getCancelleable() {
        return isCancelleable;
    }

    public void setCancelleable(Boolean cancelleable) {
        isCancelleable = cancelleable;
    }
}
