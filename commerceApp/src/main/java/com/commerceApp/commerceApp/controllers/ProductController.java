package com.commerceApp.commerceApp.controllers;

import com.commerceApp.commerceApp.dtos.productDto.*;
import com.commerceApp.commerceApp.services.ProductService;
import com.commerceApp.commerceApp.services.ProductVariationService;
import com.commerceApp.commerceApp.util.responseDtos.BaseDto;
import io.swagger.annotations.ApiOperation;
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

    @ApiOperation("To create product")
    @PostMapping("/seller/products")
    public ResponseEntity<String> createProduct(@RequestBody ProductSellerDto productSellerDto, HttpServletRequest request) {
        Principal principal = request.getUserPrincipal();
        String username = principal.getName();
        return productService.addProduct(username, productSellerDto);
    }

    @ApiOperation("To get product for seller by id")
    @GetMapping("/seller/product/{id}")
    public ProductSellerDto getProductForSeller(@PathVariable Long id, HttpServletRequest request) {
        Principal principal = request.getUserPrincipal();
        String email = principal.getName();
        return productService.getProductByIdForSeller(id, email);
    }

    @ApiOperation("Update product by id")
    @PatchMapping("/seller/product/{productId}")
    public ResponseEntity<BaseDto> updateProductById(@PathVariable Long productId, @RequestBody ProductUpdateDto productDto, HttpServletRequest request) {
        Principal principal = request.getUserPrincipal();
        String email = principal.getName();
        return productService.updateProductByProductId(productId, email, productDto);
    }

    @ApiOperation("To delete product by id")
    @DeleteMapping("/seller/product/{id}")
    public ResponseEntity<String> deleteProductById(@PathVariable Long id, HttpServletRequest request) {
        Principal principal = request.getUserPrincipal();
        String email = principal.getName();
        return productService.deleteProductById(id, email);
    }

    @ApiOperation("To get all products for seller")
    @GetMapping("/seller/products")
    public ResponseEntity<List> getAllProductsForSeller(@RequestParam(defaultValue = "0") String offset,
                                                        @RequestParam(defaultValue = "10") String size,
                                                        @RequestParam(defaultValue = "id") String sortByField,
                                                        @RequestParam(defaultValue = "ascending") String order,
                                                        @RequestParam(required = false) Long categoryId,
                                                        @RequestParam(required = false) String brand) {
        return productService.getAllProductsForSeller(offset, size, sortByField, order, categoryId, brand);
    }

    @ApiOperation("To get products for admin")
    @GetMapping("/admin/product/{productId}")
    public ResponseEntity<ProductAdminDto> getProductByIdForAdmin(@PathVariable Long productId) {
        return productService.getProductByIdForAdmin(productId);
    }

    @ApiOperation("To list all products for admin")
    @GetMapping("/admin/products")
    public ResponseEntity<List> listProductsForAdmin(@RequestParam(defaultValue = "0") String offset,
                                                     @RequestParam(defaultValue = "10") String size,
                                                     @RequestParam(defaultValue = "id") String sortByField,
                                                     @RequestParam(defaultValue = "ascending") String order,
                                                     @RequestParam(required = false) Long categoryId,
                                                     @RequestParam(required = false) String brand) {
        return productService.getAllProductsForAdmin(categoryId, offset, size, sortByField, order, brand);
    }

    @ApiOperation("Activate product by Id")
    @PutMapping("/product/activate/{id}")
    public ResponseEntity<String> activateProduct(@PathVariable Long id) {
        return productService.activateProductById(id);
    }

    @ApiOperation("Deactivate product by id")
    @PutMapping("/product/deactivate/{id}")
    public ResponseEntity<String> deactivateProduct(@PathVariable Long id) {
        return productService.deactivateproductById(id);
    }

    @ApiOperation("To get product for customer by id")
    @GetMapping("/customer/product/{productId}")
    public ResponseEntity<ProductCustomerDto> getProductForCustomer(@PathVariable Long productId) {
        return productService.getProductByIdForCustomer(productId);
    }

    @ApiOperation("To create product Variation")
    @PostMapping("/seller/product-variations")
    public ResponseEntity<BaseDto> createProductVariation(@RequestBody ProductvariationSellerDto variationDto, HttpServletRequest request) {
        Principal principal = request.getUserPrincipal();
        String username = principal.getName();
        return productVariationService.saveNewProductVariation(username, variationDto);
    }

    @ApiOperation("get Product Variation By Id ForSeller")
    @GetMapping("/seller/product-variation/{id}")
    public ResponseEntity<BaseDto> getProductVariationByIdForSeller(@PathVariable Long id, HttpServletRequest request) {
        Principal principal = request.getUserPrincipal();
        String email = principal.getName();
        return productVariationService.getProductVariationByIdForSeller(email, id);
    }

    @ApiOperation("get All ProductVariations By Product Id For Seller")
    @GetMapping("/seller/product-variations/{productId}")
    public ResponseEntity<BaseDto> getAllProductVariationsByProductIdForSeller(@PathVariable Long productId,
                                                                               @RequestParam(defaultValue = "0") String offset,
                                                                               @RequestParam(defaultValue = "10") String size,
                                                                               @RequestParam(defaultValue = "id") String sortByField,
                                                                               @RequestParam(defaultValue = "ascending") String order,
                                                                               HttpServletRequest request) {
        Principal principal = request.getUserPrincipal();
        String email = principal.getName();
        return productVariationService.getAllProductVariationsByProductIdForSeller(email, productId, offset, size, sortByField, order);
    }

    @ApiOperation("update Product Variation By Id")
    @PatchMapping("/seller/product-variation/{variationId}")
    public ResponseEntity<BaseDto> updateProductVariationById(
            @PathVariable Long variationId,
            @RequestBody ProductVariationUpdateDto variationDto,
            HttpServletRequest request) {
        Principal principal = request.getUserPrincipal();
        String email = principal.getName();
        return productVariationService.updateProductVariationById(variationId, email, variationDto);
    }

    @ApiOperation("get Similar Products By Product Id For Customer")
    @GetMapping("/customer/similar-products/{productId}")
    public ResponseEntity<BaseDto> getSimilarProductsByProductIdForCustomer(@PathVariable Long productId,
                                                                            @RequestParam(defaultValue = "0") String offset,
                                                                            @RequestParam(defaultValue = "10") String size,
                                                                            @RequestParam(defaultValue = "id") String sortByField,
                                                                            @RequestParam(defaultValue = "ascending") String order) {

        return productVariationService.getAllSimilarProductsByProductId(productId, offset, size, sortByField, order);
    }


}
