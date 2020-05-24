package com.commerceApp.commerceApp.controllers;

import com.commerceApp.commerceApp.dtos.CartDto;
import com.commerceApp.commerceApp.dtos.UpdateCardDto;
import com.commerceApp.commerceApp.services.CartService;
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

@Api(value = "CartController", description = "REST APIs related to cart")
@RestController
public class CartController {
    @Autowired
    CartService cartService;

    @ApiOperation(value = "add to cart", authorizations = {@Authorization(value = "Bearer")})

    @PostMapping(value = "/add/cart", produces = "application/json")
    public ResponseEntity<BaseDto> addTocart(CartDto cartDto, @ApiIgnore HttpServletRequest request) {
        Principal principal = request.getUserPrincipal();
        String email = principal.getName();
        return new ResponseEntity<>(cartService.addToCart(cartDto, email), HttpStatus.OK);
    }

    @ApiOperation(value = "view cart", authorizations = {@Authorization(value = "Bearer")})
    @GetMapping(value = "/cart", produces = "application/json")
    public ResponseEntity<BaseDto> viewcart(@ApiIgnore HttpServletRequest request) {
        Principal principal = request.getUserPrincipal();
        String email = principal.getName();
        return new ResponseEntity<>(cartService.viewCart(email), HttpStatus.OK);
    }

    @ApiOperation(value = "Update cart", authorizations = {@Authorization(value = "Bearer")})
    @PatchMapping(value = "/cart", produces = "application/json")
    public ResponseEntity<BaseDto> updateCart(UpdateCardDto updateCardDto, @ApiIgnore HttpServletRequest request) {
        Principal principal = request.getUserPrincipal();
        String email = principal.getName();
        return new ResponseEntity<>(cartService.updateCart(updateCardDto, email), HttpStatus.OK);
    }


}
