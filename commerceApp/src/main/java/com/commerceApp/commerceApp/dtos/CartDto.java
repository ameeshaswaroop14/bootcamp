package com.commerceApp.commerceApp.dtos;

import com.commerceApp.commerceApp.models.product.ProductVariation;

public class CartDto {
    private Long id;
    private Integer quantity;
    private Long productVarId;

    public Long getProductVarId() {
        return productVarId;
    }

    public void setProductVarId(Long productVarId) {
        this.productVarId = productVarId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }


}