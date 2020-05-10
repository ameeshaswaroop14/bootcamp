package com.commerceApp.commerceApp.controllers;

import com.commerceApp.commerceApp.dtos.productDto.*;
import com.commerceApp.commerceApp.services.ProductService;
import com.commerceApp.commerceApp.services.ProductVariationService;
import com.commerceApp.commerceApp.util.responseDtos.BaseDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.List;
@Api(value = "ProductController", description = "REST APIs related to Product")
@RestController
public class ProductController {

    @Autowired
    ProductService productService;
    @Autowired
    ProductVariationService productVariationService;

    @ApiOperation(value = "To create product", authorizations = { @Authorization(value="Bearer") })
    @PostMapping(value = "/seller/products",produces = "application/json")
    public ResponseEntity<BaseDto> createProduct(@RequestBody ProductSellerDto productSellerDto,@ApiIgnore HttpServletRequest request) {
        Principal principal = request.getUserPrincipal();
        String username = principal.getName();
        return productService.saveNewProduct(username, productSellerDto);
    }

    @ApiOperation(value = "To get product for seller by id", authorizations = { @Authorization(value="Bearer") })
    @GetMapping(value = "/seller/product/{id}",produces = "application/json")
    public ProductSellerDto getProductForSeller(@PathVariable Long id,@ApiIgnore HttpServletRequest request) {
        Principal principal = request.getUserPrincipal();
        String email = principal.getName();
        return productService.getProductByIdForSeller(id, email);
    }

    @ApiOperation(value = "For seller to update product by id", authorizations = { @Authorization(value="Bearer") })
    @PatchMapping(value = "/seller/product/{productId}",produces = "application/json")
    public ResponseEntity<BaseDto> updateProductById(@PathVariable Long productId, @RequestBody ProductUpdateDto productDto,@ApiIgnore HttpServletRequest request) {
        Principal principal = request.getUserPrincipal();
        String email = principal.getName();
        return productService.updateProductByProductId(productId, email, productDto);
    }

    @ApiOperation(value = "For seller To delete a product by id", authorizations = { @Authorization(value="Bearer") })
    @DeleteMapping("/seller/product/{id}")
    public ResponseEntity<String> deleteProductById(@PathVariable Long id,@ApiIgnore HttpServletRequest request) {
        Principal principal = request.getUserPrincipal();
        String email = principal.getName();
        return productService.deleteProductById(id, email);
    }

    @ApiOperation(value = "For seller to get list of products", authorizations = { @Authorization(value="Bearer") })
    @GetMapping("/seller/products")
    public ResponseEntity<List> getAllProductsForSeller(@RequestParam(defaultValue = "0") String offset,
                                                        @RequestParam(defaultValue = "10") String size,
                                                        @RequestParam(defaultValue = "id") String sortByField,
                                                        @RequestParam(defaultValue = "ascending") String order,
                                                        @RequestParam(required = false) Long categoryId,
                                                        @RequestParam(required = false) String brand) {
        return productService.getAllProductsForSeller(offset, size, sortByField, order, categoryId, brand);
    }

    @ApiOperation(value = "To get product for admin by id", authorizations = { @Authorization(value="Bearer") })
    @GetMapping("/admin/product/{productId}")
    public ResponseEntity<ProductAdminDto> getProductByIdForAdmin(@PathVariable Long productId) {
        return productService.getProductByIdForAdmin(productId);
    }

    @ApiOperation(value = "To get all products for admin", authorizations = { @Authorization(value="Bearer") })
    @GetMapping("/admin/products")
    public ResponseEntity<List> listProductsForAdmin(@RequestParam(defaultValue = "0") String offset,
                                                     @RequestParam(defaultValue = "10") String size,
                                                     @RequestParam(defaultValue = "id") String sortByField,
                                                     @RequestParam(defaultValue = "ascending") String order,
                                                     @RequestParam(required = false) Long categoryId,
                                                     @RequestParam(required = false) String brand) {
        return productService.getAllProductsForAdmin(categoryId, offset, size, sortByField, order, brand);
    }

    @ApiOperation(value = "To activate product by id", authorizations = { @Authorization(value="Bearer") })
    @PutMapping(value = "/product/activate/{id}",produces = "application/json")
    public ResponseEntity<String> activateProduct(@PathVariable Long id) {
        return productService.activateProductById(id);
    }

    @ApiOperation(value = "To deactivate product by id", authorizations = { @Authorization(value="Bearer") })
    @PutMapping(value = "/product/deactivate/{id}",produces = "application/json")
    public ResponseEntity<String> deactivateProduct(@PathVariable Long id) {
        return productService.deactivateproductById(id);
    }

    @ApiOperation(value = "To get product for customer by id", authorizations = { @Authorization(value="Bearer") })
    @GetMapping(value = "/customer/product/{productId}",produces = "application/json")
    public ResponseEntity<ProductCustomerDto> getProductForCustomer(@PathVariable Long productId) {
        return productService.getProductByIdForCustomer(productId);
    }


    @ApiOperation(value = "To create product variation", authorizations = { @Authorization(value="Bearer") })
    @PostMapping("/seller/product-variations")
    public ResponseEntity<BaseDto> createProductVariation(@RequestBody ProductvariationSellerDto variationDto, HttpServletRequest request) {
        Principal principal = request.getUserPrincipal();
        String username = principal.getName();
        return productVariationService.saveNewProductVariation(username, variationDto);
    }

    @ApiOperation(value = "To get product variation by id for seller", authorizations = { @Authorization(value="Bearer") })
    @GetMapping("/seller/product-variation/{id}")
    public ResponseEntity<BaseDto> getProductVariationByIdForSeller(@PathVariable Long id, HttpServletRequest request) {
        Principal principal = request.getUserPrincipal();
        String email = principal.getName();
        return productVariationService.getProductVariationByIdForSeller(email, id);
    }


    @ApiOperation(value = "To get all product variations  for seller by id", authorizations = { @Authorization(value="Bearer") })
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


    @ApiOperation(value = "To update product variation by id", authorizations = { @Authorization(value="Bearer") })
    @PatchMapping("/seller/product-variation/{variationId}")
    public ResponseEntity<BaseDto> updateProductVariationById(
            @PathVariable Long variationId,
            @RequestBody ProductVariationUpdateDto variationDto,
            HttpServletRequest request) {
        Principal principal = request.getUserPrincipal();
        String email = principal.getName();
        return productVariationService.updateProductVariationById(variationId, email, variationDto);
    }

    @ApiOperation(value = "To get similar products by id", authorizations = { @Authorization(value="Bearer") })
    @GetMapping("/customer/similar-products/{productId}")
    public ResponseEntity<BaseDto> getSimilarProductsByProductIdForCustomer(@PathVariable Long productId,
                                                                            @RequestParam(defaultValue = "0") String offset,
                                                                            @RequestParam(defaultValue = "10") String size,
                                                                            @RequestParam(defaultValue = "id") String sortByField,
                                                                            @RequestParam(defaultValue = "ascending") String order) {

        return productVariationService.getAllSimilarProductsByProductId(productId, offset, size, sortByField, order);
    }


}
