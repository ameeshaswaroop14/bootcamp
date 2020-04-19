package com.commerceApp.commerceApp.controllers;

import com.commerceApp.commerceApp.dtos.ProductAdminDto;
import com.commerceApp.commerceApp.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AdminProductController {
    @Autowired
    ProductService productService;

    @GetMapping("/admin/product/{productId}")
    public ResponseEntity<ProductAdminDto> getProductByIdForAdmin(@PathVariable Long productId){
        return productService.getProductByIdForAdmin(productId);
    }
    @GetMapping("/admin/products")
    public ResponseEntity<List> getProductByIdForAdmin(@RequestParam(defaultValue = "0") String offset,
                                                       @RequestParam(defaultValue = "10") String size,
                                                       @RequestParam(defaultValue = "id") String sortByField,
                                                       @RequestParam(defaultValue = "ascending") String order,
                                                       @RequestParam(required = false) Long categoryId,
                                                       @RequestParam(required = false) String brand){
        return productService.getAllProductsForAdmin(categoryId, offset, size, sortByField, order, brand);
    }
    @PutMapping("/product/activate/{id}")
    public ResponseEntity<String> activateProduct(@PathVariable Long id){
        return productService.activateProductById(id);
    }

    @PutMapping("/product/deactivate/{id}")
    public ResponseEntity<String> deactivateProduct(@PathVariable Long id){
        return productService.deactivateproductById(id);
    }
}
