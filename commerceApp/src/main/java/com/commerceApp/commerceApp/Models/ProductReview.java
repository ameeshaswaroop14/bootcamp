package com.commerceApp.commerceApp.Models;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class ProductReview {
    @ManyToOne
    @JoinColumn(name = "CUSTOMER_USER_ID")
    private Customer customer;
    private String REVIEW;
    private Double RATING;


}
