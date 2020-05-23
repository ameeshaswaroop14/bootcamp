package com.commerceApp.commerceApp.services;

import com.commerceApp.commerceApp.dtos.CartDto;
import com.commerceApp.commerceApp.dtos.ResponseCartDto;
import com.commerceApp.commerceApp.models.Cart;
import com.commerceApp.commerceApp.models.Customer;
import com.commerceApp.commerceApp.models.product.ProductVariation;
import com.commerceApp.commerceApp.repositories.CartRepository;
//import com.commerceApp.commerceApp.repositories.CustomCartRepository;
import com.commerceApp.commerceApp.repositories.CustomCartRepository;
import com.commerceApp.commerceApp.repositories.productRepos.ProductVariationRepository;
import com.commerceApp.commerceApp.repositories.userRepos.CustomerRepository;
import com.commerceApp.commerceApp.util.responseDtos.BaseDto;
import com.commerceApp.commerceApp.util.responseDtos.ErrorDto;
import com.commerceApp.commerceApp.util.responseDtos.ResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import static com.commerceApp.commerceApp.util.EntityDtoMapping.*;

@Service
public class CartService {
    @Autowired
    ProductVariationRepository productVariationRepository;
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    CartRepository cartRepository;
    @Autowired
    CustomCartRepository customCartRepository;

    public BaseDto addToCart(CartDto cartDto, String username) {
        ProductVariation product = productVariationRepository.findById(cartDto.getId()).get();
        if (product == null)
            return new ErrorDto("Not found", null);

        Cart cart = toCart(cartDto);
        Customer customer = customerRepository.findByEmail(username);

        cart.setProductVariation(product);
        cart.setQuantity(cartDto.getQuantity());
        cart.setUser(customer);
        cartRepository.save(cart);
        return new ResponseDto<>("Added", null);
    }

    public BaseDto viewCart(String username) {
        Customer customer = customerRepository.findByEmail(username);
        if (customer == null)
            return new ErrorDto("Invalid operation", "user not found");
        Cart cart = customCartRepository.findCartByUserId(customer.getId());


        if (cart == null)
            return new ErrorDto("Cart not found", null);
        CartDto cartDto = toCartDto(cart);


        return new ResponseDto<>("Cart details", cartDto);

    }


}
