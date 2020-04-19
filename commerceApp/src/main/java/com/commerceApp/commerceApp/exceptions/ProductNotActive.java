package com.commerceApp.commerceApp.exceptions;

public class ProductNotActive extends RuntimeException {
    public ProductNotActive(String message) {
        super(message);
    }
}
