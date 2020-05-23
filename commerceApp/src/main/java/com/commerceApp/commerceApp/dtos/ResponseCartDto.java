package com.commerceApp.commerceApp.dtos;

import com.commerceApp.commerceApp.models.Cart;
import com.commerceApp.commerceApp.models.product.ProductVariation;

public class ResponseCartDto {
    private Long id;
    private Integer quantity;
    private ProductVariation productVariation;

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

    public ProductVariation getProductVariation() {
        return productVariation;
    }

    public void setProductVariation(ProductVariation productVariation) {
        this.productVariation = productVariation;
    }
}