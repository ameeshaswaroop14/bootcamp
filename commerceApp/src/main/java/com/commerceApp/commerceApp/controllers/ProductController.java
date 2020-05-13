package com.commerceApp.commerceApp.controllers;

import com.commerceApp.commerceApp.bootloader.Bootstrap;
import com.commerceApp.commerceApp.dtos.productDto.*;
import com.commerceApp.commerceApp.services.ProductService;
import com.commerceApp.commerceApp.services.ProductVariationService;
import com.commerceApp.commerceApp.util.responseDtos.BaseDto;
import com.commerceApp.commerceApp.util.responseDtos.ResponseDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
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
    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    @ApiOperation(value = "To create product", authorizations = {@Authorization(value = "Bearer")})
    @PostMapping(value = "/seller/products", produces = "application/json")
    public ResponseEntity<BaseDto> createProduct(@RequestBody ProductSellerDto productSellerDto, @ApiIgnore HttpServletRequest request) {
        Principal principal = request.getUserPrincipal();
        String username = principal.getName();
        return new ResponseEntity<>(productService.saveNewProduct(username,productSellerDto),HttpStatus.OK);

    }

    //  @Cacheable(value = "product", key = "#id", condition = "#id!=null", unless = "#result==null")
    @ApiOperation(value = "To get product for seller by id", authorizations = {@Authorization(value = "Bearer")})
    @GetMapping(value = "/seller/product/{id}", produces = "application/json")
    public ResponseEntity<BaseDto> getProductForSeller(@PathVariable Long id, @ApiIgnore HttpServletRequest request) {
        Principal principal = request.getUserPrincipal();
        String email = principal.getName();
        return new ResponseEntity<>(productService.getProductByIdForSeller(id,email),HttpStatus.OK);

    }

    @ApiOperation(value = "For seller to update product by id", authorizations = {@Authorization(value = "Bearer")})
    @PatchMapping(value = "/seller/product/{productId}", produces = "application/json")
    public ResponseEntity<BaseDto> updateProductById(@PathVariable Long productId, @RequestBody ProductUpdateDto productDto, @ApiIgnore HttpServletRequest request) {
        Principal principal = request.getUserPrincipal();
        String email = principal.getName();
        return new ResponseEntity<>(productService.updateProductByProductId(productId,email,productDto),HttpStatus.OK);

    }

    @ApiOperation(value = "For seller To delete a product by id", authorizations = {@Authorization(value = "Bearer")})
    @DeleteMapping(value = "/seller/product/{id}", produces = "application/json")
    public ResponseEntity<BaseDto> deleteProductById(@PathVariable Long id, @ApiIgnore HttpServletRequest request) {
        Principal principal = request.getUserPrincipal();
        String email = principal.getName();
        return new ResponseEntity<>(productService.deleteProductById(id,email),HttpStatus.OK);

    }

    @ApiOperation(value = "For seller to get list of products", authorizations = {@Authorization(value = "Bearer")})
    @GetMapping(value = "/seller/products", produces = "application/json")
    public ResponseEntity<BaseDto> getAllProductsForSeller(@RequestParam(defaultValue = "0") String offset,
                                                           @RequestParam(defaultValue = "10") String size,
                                                           @RequestParam(defaultValue = "id") String sortByField,
                                                           @RequestParam(defaultValue = "ascending") String order,
                                                           @RequestParam(required = false) Long categoryId,
                                                           @RequestParam(required = false) String brand) {
        logger.info("Getting user with ID {}.", categoryId);
        System.out.println("///////////////////" + categoryId);
        BaseDto response = new ResponseDto<>(null, productService.getAllProductsForSeller(offset, size, sortByField, order, categoryId, brand));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @ApiOperation(value = "To get product for admin by id", authorizations = {@Authorization(value = "Bearer")})
    @GetMapping("/admin/product/{productId}")
    public ResponseEntity<BaseDto> getProductByIdForAdmin(@PathVariable Long productId) {
        return new ResponseEntity<>(productService.getProductByIdForAdmin(productId),HttpStatus.OK);

    }

    @ApiOperation(value = "To get all products for admin", authorizations = {@Authorization(value = "Bearer")})
    @GetMapping(value = "/admin/products", produces = "application/json")
    public ResponseEntity<BaseDto> listProductsForAdmin(@RequestParam(defaultValue = "0") String offset,
                                                        @RequestParam(defaultValue = "10") String size,
                                                        @RequestParam(defaultValue = "id") String sortByField,
                                                        @RequestParam(defaultValue = "ascending") String order,
                                                        @RequestParam(required = false) Long categoryId,
                                                        @RequestParam(required = false) String brand) {
        BaseDto response = new ResponseDto<>(null, productService.getAllProductsForAdmin(categoryId, offset, size, sortByField, order, brand));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @ApiOperation(value = "To activate product by id", authorizations = {@Authorization(value = "Bearer")})
    @PutMapping(value = "/product/activate/{id}", produces = "application/json")
    public ResponseEntity<BaseDto> activateProduct(@PathVariable Long id) {
        return new ResponseEntity<>(productService.activateProductById(id),HttpStatus.OK);

    }

    @ApiOperation(value = "To deactivate product by id", authorizations = {@Authorization(value = "Bearer")})
    @PutMapping(value = "/product/deactivate/{id}", produces = "application/json")
    public ResponseEntity<BaseDto> deactivateProduct(@PathVariable Long id) {
        return new ResponseEntity<>(productService.deactivateproductById(id),HttpStatus.OK);

    }

    @ApiOperation(value = "To get product for customer by id", authorizations = {@Authorization(value = "Bearer")})
    @GetMapping(value = "/customer/product/{productId}", produces = "application/json")
    public ResponseEntity<BaseDto> getProductForCustomer(@PathVariable Long productId) {
        return new ResponseEntity<>(productService.getProductByIdForCustomer(productId),HttpStatus.OK);

    }


    @ApiOperation(value = "To create product variation", authorizations = {@Authorization(value = "Bearer")})
    @PostMapping(value = "/seller/product-variations", produces = "application/json")
    public ResponseEntity<BaseDto> createProductVariation(@RequestBody ProductvariationSellerDto variationDto, HttpServletRequest request) {
        Principal principal = request.getUserPrincipal();
        String username = principal.getName();
        return new ResponseEntity<>(productVariationService.saveNewProductVariation(username,variationDto),HttpStatus.OK);

    }

    @ApiOperation(value = "To get product variation by id for seller", authorizations = {@Authorization(value = "Bearer")})
    @GetMapping(value = "/seller/product-variation/{id}", produces = "application/json")
    public ResponseEntity<BaseDto> getProductVariationByIdForSeller(@PathVariable Long id, HttpServletRequest request) {
        Principal principal = request.getUserPrincipal();
        String email = principal.getName();
        return new ResponseEntity<>(productVariationService.getProductVariationByIdForSeller(email,id),HttpStatus.OK);

    }


    @ApiOperation(value = "To get all product variations  for seller by id", authorizations = {@Authorization(value = "Bearer")})
    @GetMapping(value = "/seller/product-variations/{productId}", produces = "application/json")
    public ResponseEntity<BaseDto> getAllProductVariationsByProductIdForSeller(@PathVariable Long productId,
                                                                               @RequestParam(defaultValue = "0") String offset,
                                                                               @RequestParam(defaultValue = "10") String size,
                                                                               @RequestParam(defaultValue = "id") String sortByField,
                                                                               @RequestParam(defaultValue = "ascending") String order,
                                                                               HttpServletRequest request) {
        Principal principal = request.getUserPrincipal();
        String email = principal.getName();
        return new ResponseEntity<>(productVariationService.getAllProductVariationsByProductIdForSeller(email, productId, offset, size, sortByField, order), HttpStatus.OK);

    }


    @ApiOperation(value = "To update product variation by id", authorizations = {@Authorization(value = "Bearer")})
    @PatchMapping(value = "/seller/product-variation/{variationId}", produces = "application/json")
    public ResponseEntity<BaseDto> updateProductVariationById(
            @PathVariable Long variationId,
            @RequestBody ProductVariationUpdateDto variationDto,
            HttpServletRequest request) {
        Principal principal = request.getUserPrincipal();
        String email = principal.getName();
        return new ResponseEntity<>(productVariationService.updateProductVariationById(variationId,email,variationDto),HttpStatus.OK);
        
    }

    @ApiOperation(value = "To get similar products by id", authorizations = {@Authorization(value = "Bearer")})
    @GetMapping(value = "/customer/similar-products/{productId}", produces = "application/json")
    public ResponseEntity<BaseDto> getSimilarProductsByProductIdForCustomer(@PathVariable Long productId,
                                                                            @RequestParam(defaultValue = "0") String offset,
                                                                            @RequestParam(defaultValue = "10") String size,
                                                                            @RequestParam(defaultValue = "id") String sortByField,
                                                                            @RequestParam(defaultValue = "ascending") String order) {
        return new ResponseEntity<>(productVariationService.getAllSimilarProductsByProductId(productId, offset, size, sortByField, order), HttpStatus.OK);


    }


}
