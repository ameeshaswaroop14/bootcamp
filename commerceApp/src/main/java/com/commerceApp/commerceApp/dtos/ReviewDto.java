package com.commerceApp.commerceApp.dtos;

import com.commerceApp.commerceApp.models.Customer;
import com.commerceApp.commerceApp.models.product.Product;

public class ReviewDto {
    private String review;
    private Double rating;

    private Long productId;

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }
}
