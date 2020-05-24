package com.commerceApp.commerceApp.dtos;

public class UpdateCardDto {
    private Long cartId;
    private Integer quantity;
    private Long productVarId;

    public UpdateCardDto(Long cartId, Integer quantity, Long productVarId) {
        this.cartId = cartId;
        this.quantity = quantity;
        this.productVarId = productVarId;
    }

    public Long getCartId() {
        return cartId;
    }

    public void setCartId(Long cartId) {
        this.cartId = cartId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Long getProductVarId() {
        return productVarId;
    }

    public void setProductVarId(Long productVarId) {
        this.productVarId = productVarId;
    }
}
