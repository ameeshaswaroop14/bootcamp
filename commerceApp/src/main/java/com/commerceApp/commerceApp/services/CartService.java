package com.commerceApp.commerceApp.services;

import com.commerceApp.commerceApp.dtos.CartDto;
import com.commerceApp.commerceApp.dtos.ResponseCartDto;
import com.commerceApp.commerceApp.dtos.ReviewDto;
import com.commerceApp.commerceApp.dtos.UpdateCardDto;
import com.commerceApp.commerceApp.dtos.productDto.ProductAdminDto;
import com.commerceApp.commerceApp.models.Cart;
import com.commerceApp.commerceApp.models.Customer;
import com.commerceApp.commerceApp.models.product.ProductVariation;
import com.commerceApp.commerceApp.repositories.CartRepository;
//import com.commerceApp.commerceApp.repositories.CustomCartRepository;
import com.commerceApp.commerceApp.repositories.CustomCartRepository;
import com.commerceApp.commerceApp.repositories.productRepos.ProductVariationRepository;
import com.commerceApp.commerceApp.repositories.userRepos.CustomerRepository;
import com.commerceApp.commerceApp.util.EntityDtoMapping;
import com.commerceApp.commerceApp.util.responseDtos.BaseDto;
import com.commerceApp.commerceApp.util.responseDtos.ErrorDto;
import com.commerceApp.commerceApp.util.responseDtos.ResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.WebRequest;


import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    MessageSource messageSource;


    public BaseDto addToCart(CartDto cartDto, String username, HttpServletRequest request) {
        String message;
        String error;
        ProductVariation product = productVariationRepository.findById(cartDto.getProductVarId()).get();
        if (product == null) {
            error = messageSource.getMessage("message.notfound", null, request.getLocale());
            return new ErrorDto(error, null);
        }

        Cart cart = toCart(cartDto);
        Customer customer = customerRepository.findByEmail(username);


        cart.setProductVariation(product);
        cart.setQuantity(cartDto.getQuantity());
        cart.setUser(customer);
        cartRepository.save(cart);
        message = messageSource.getMessage("message.added", null, request.getLocale());
        return new ResponseDto<>(message, null);
    }

    public BaseDto viewCart(String username) {
        Customer customer = customerRepository.findByEmail(username);

        List<Cart> carts = cartRepository.findCartByUserId(customer.getId());

        List<CartDto> cartDtoList = new ArrayList<>();
        carts.forEach(carts1 -> cartDtoList.add(new CartDto(
                carts1.getUser().getId(), carts1.getQuantity(), carts1.getProductVariation().getId())));

        return new ResponseDto<>(null, cartDtoList);

    }

    public BaseDto updateCart(UpdateCardDto updateCardDto, String username, HttpServletRequest request) {
        String message;
        String error;
        Optional<Cart> cart = cartRepository.findById(updateCardDto.getCartId());
        if (cart == null) {
            error = messageSource.getMessage("message.notValid", null, request.getLocale());
            message=messageSource.getMessage("message.cardIdNotFound",null,request.getLocale());
            return new ErrorDto(error, message);
        }
        ProductVariation product = productVariationRepository.findById(updateCardDto.getProductVarId()).get();
        if (product == null){
            error=messageSource.getMessage("message.notfound",null,request.getLocale());
            return new ErrorDto(error, null);}
        Customer customer = customerRepository.findByEmail(username);
        Cart savedCart = cart.get();
        savedCart = toCart(updateCardDto);
        savedCart.setQuantity(updateCardDto.getQuantity());
        savedCart.setProductVariation(product);
        savedCart.setUser(customer);
        cartRepository.save(savedCart);


        return new ResponseDto<>("Updated", null);
    }

    public BaseDto removeFromCart(Long id, String username,HttpServletRequest request) {
        String error;
        String message;
        Optional<Cart> cart = cartRepository.findById(id);
        if (cart == null) {
            error=messageSource.getMessage("message.notValid",null,request.getLocale());
            message=messageSource.getMessage("message.cardIdNotFound",null,request.getLocale());
            return new ErrorDto(error, message);
        }

        Cart savedCart = cart.get();
        savedCart.setDeleted(true);
        cartRepository.delete(savedCart);
        return new ResponseDto<>("Deleted", null);

    }

}




