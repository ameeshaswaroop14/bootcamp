package com.commerceApp.commerceApp.controllers;

import com.commerceApp.commerceApp.dtos.ProductCustomerDto;
import com.commerceApp.commerceApp.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerProductController {

    @Autowired
    ProductService productService;
    @GetMapping("/customer/product/{productId}")
    public ResponseEntity<ProductCustomerDto> getProductForCustomer(@PathVariable Long productId){
        return productService.getProductByIdForCustomer(productId);
    }

}
