package com.commerceApp.commerceApp.controllers;

import com.commerceApp.commerceApp.dtos.ProductSellerDto;
import com.commerceApp.commerceApp.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.List;

@RestController
public class ProductController {

    @Autowired
    ProductService productService;
    @PostMapping("/seller/products")
    public ResponseEntity<String> createProduct(@RequestBody ProductSellerDto productSellerDto, HttpServletRequest request){
        Principal principal = request.getUserPrincipal();
        String username = principal.getName();
        return productService.addProduct(username,productSellerDto);
    }

    @GetMapping("/seller/product/{id}")
    public ProductSellerDto getProductForSeller(@PathVariable Long id, HttpServletRequest request){
        Principal principal = request.getUserPrincipal();
        String email = principal.getName();
        return productService.getProductByIdForSeller(id, email);
    }

    @DeleteMapping("/seller/product/{id}")
    public ResponseEntity<String> deleteProductById(@PathVariable Long id, HttpServletRequest request){
        Principal principal = request.getUserPrincipal();
        String email = principal.getName();
        return productService.deleteProductById(id,email);
    }

    @GetMapping("/seller/products")
    public ResponseEntity<List> getAllProductsForSeller(@RequestParam(defaultValue = "0") String offset,
                                                        @RequestParam(defaultValue = "10") String size,
                                                        @RequestParam(defaultValue = "id") String sortByField,
                                                        @RequestParam(defaultValue = "ascending") String order,
                                                        @RequestParam(required = false) Long categoryId,
                                                        @RequestParam(required = false) String brand){
        return productService.getAllProductsForSeller(offset, size, sortByField, order, categoryId, brand);
    }




}
