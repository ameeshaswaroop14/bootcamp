package com.commerceApp.commerceApp.controllers;

import com.commerceApp.commerceApp.dtos.productDto.ProductAdminDto;
import com.commerceApp.commerceApp.dtos.productDto.ProductCustomerDto;
import com.commerceApp.commerceApp.dtos.productDto.ProductSellerDto;
import com.commerceApp.commerceApp.dtos.productDto.ProductvariationSellerDto;
import com.commerceApp.commerceApp.services.ProductService;
import com.commerceApp.commerceApp.services.ProductVariationService;
import com.commerceApp.commerceApp.util.responseDtos.BaseDto;
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
    @Autowired
    ProductVariationService productVariationService;

    @PostMapping("/seller/products")
    public ResponseEntity<String> createProduct(@RequestBody ProductSellerDto productSellerDto, HttpServletRequest request) {
        Principal principal = request.getUserPrincipal();
        String username = principal.getName();
        return productService.addProduct(username, productSellerDto);
    }

    @GetMapping("/seller/product/{id}")
    public ProductSellerDto getProductForSeller(@PathVariable Long id, HttpServletRequest request) {
        Principal principal = request.getUserPrincipal();
        String email = principal.getName();
        return productService.getProductByIdForSeller(id, email);
    }

    @DeleteMapping("/seller/product/{id}")
    public ResponseEntity<String> deleteProductById(@PathVariable Long id, HttpServletRequest request) {
        Principal principal = request.getUserPrincipal();
        String email = principal.getName();
        return productService.deleteProductById(id, email);
    }

    @GetMapping("/seller/products")
    public ResponseEntity<List> getAllProductsForSeller(@RequestParam(defaultValue = "0") String offset,
                                                        @RequestParam(defaultValue = "10") String size,
                                                        @RequestParam(defaultValue = "id") String sortByField,
                                                        @RequestParam(defaultValue = "ascending") String order,
                                                        @RequestParam(required = false) Long categoryId,
                                                        @RequestParam(required = false) String brand) {
        return productService.getAllProductsForSeller(offset, size, sortByField, order, categoryId, brand);
    }

    @GetMapping("/admin/product/{productId}")
    public ResponseEntity<ProductAdminDto> getProductByIdForAdmin(@PathVariable Long productId) {
        return productService.getProductByIdForAdmin(productId);
    }

    @GetMapping("/admin/products")
    public ResponseEntity<List> listProductsForAdmin(@RequestParam(defaultValue = "0") String offset,
                                                     @RequestParam(defaultValue = "10") String size,
                                                     @RequestParam(defaultValue = "id") String sortByField,
                                                     @RequestParam(defaultValue = "ascending") String order,
                                                     @RequestParam(required = false) Long categoryId,
                                                     @RequestParam(required = false) String brand) {
        return productService.getAllProductsForAdmin(categoryId, offset, size, sortByField, order, brand);
    }

    @PutMapping("/product/activate/{id}")
    public ResponseEntity<String> activateProduct(@PathVariable Long id) {
        return productService.activateProductById(id);
    }

    @PutMapping("/product/deactivate/{id}")
    public ResponseEntity<String> deactivateProduct(@PathVariable Long id) {
        return productService.deactivateproductById(id);
    }

    @GetMapping("/customer/product/{productId}")
    public ResponseEntity<ProductCustomerDto> getProductForCustomer(@PathVariable Long productId) {
        return productService.getProductByIdForCustomer(productId);
    }

    @PostMapping("/seller/product-variations")
    public ResponseEntity<BaseDto> createProductVariation(@RequestBody ProductvariationSellerDto variationDto, HttpServletRequest request) {
        Principal principal = request.getUserPrincipal();
        String username = principal.getName();
        return productVariationService.saveNewProductVariation(username, variationDto);
    }

    @GetMapping("/seller/product-variation/{id}")
    public ResponseEntity<BaseDto> getProductVariationByIdForSeller(@PathVariable Long id, HttpServletRequest request) {
        Principal principal = request.getUserPrincipal();
        String email = principal.getName();
        return productVariationService.getProductVariationByIdForSeller(email, id);
    }
    @GetMapping("/seller/product-variations/{productId}")
    public ResponseEntity<BaseDto> getAllProductVariationsByProductIdForSeller(@PathVariable Long productId,
                                                                              @RequestParam(defaultValue = "0") String offset,
                                                                              @RequestParam(defaultValue = "10") String size,
                                                                              @RequestParam(defaultValue = "id") String sortByField,
                                                                              @RequestParam(defaultValue = "ascending") String order,
                                                                              HttpServletRequest request){
        Principal principal = request.getUserPrincipal();
        String email = principal.getName();
        return productVariationService.getAllProductVariationsByProductIdForSeller(email, productId, offset, size, sortByField, order);
    }



}
