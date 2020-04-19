package com.commerceApp.commerceApp.exceptions;

public class ProductDoesNotExists extends RuntimeException {
    public ProductDoesNotExists(String message) {
        super(message);
    }
}
