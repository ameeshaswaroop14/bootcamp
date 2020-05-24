package com.commerceApp.commerceApp.controllers;

import com.commerceApp.commerceApp.dtos.ReviewDto;
import com.commerceApp.commerceApp.services.ReviewService;
import com.commerceApp.commerceApp.util.responseDtos.BaseDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@Api(value = "ProductReviewController", description = "REST APIs related to ProductReview")
@RestController
public class ProductReviewController {
    @Autowired
    ReviewService reviewService;

    @ApiOperation(value = "To create product review", authorizations = {@Authorization(value = "Bearer")})
    @PostMapping(value = "/product/add-review", produces = "application/json")
    public ResponseEntity<BaseDto> addProductReview(@RequestBody ReviewDto reviewDto, @ApiIgnore HttpServletRequest request) {
        Principal principal = request.getUserPrincipal();
        String username = principal.getName();
        return new ResponseEntity<>(reviewService.addProductReview(reviewDto, username), HttpStatus.OK);
    }

    @ApiOperation(value = "To get product review by id", authorizations = {@Authorization(value = "Bearer")})
    @GetMapping(value = "/product-review/{id}", produces = "application/json")
    public ResponseEntity<BaseDto> getProductReview(@PathVariable Long id, @ApiIgnore HttpServletRequest request) {
        Principal principal = request.getUserPrincipal();
        String email = principal.getName();
        return new ResponseEntity<>(reviewService.getProductReviewByProductId(id, email), HttpStatus.OK);

    }

    @ApiOperation(value = "To get product review By customer id", authorizations = {@Authorization(value = "Bearer")})
    @GetMapping(value = "/product-review-customer/{id}", produces = "application/json")
    public ResponseEntity<BaseDto> getProductReviewByCustomer( @ApiIgnore HttpServletRequest request) {
        Principal principal = request.getUserPrincipal();
        String email = principal.getName();
        return new ResponseEntity<>(reviewService.getProductReviewByCustomer( email), HttpStatus.OK);

    }


}
