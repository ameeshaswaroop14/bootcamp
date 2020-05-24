package com.commerceApp.commerceApp.services;

import com.commerceApp.commerceApp.dtos.ReviewDto;
import com.commerceApp.commerceApp.dtos.productDto.ProductSellerDto;
import com.commerceApp.commerceApp.exceptions.ProductAlreadyExists;
import com.commerceApp.commerceApp.exceptions.ProductDoesNotExists;
import com.commerceApp.commerceApp.models.Customer;
import com.commerceApp.commerceApp.models.Seller;
import com.commerceApp.commerceApp.models.category.Category;
import com.commerceApp.commerceApp.models.product.Product;
import com.commerceApp.commerceApp.models.product.ProductReview;
import com.commerceApp.commerceApp.repositories.CustomReviewRepository;
import com.commerceApp.commerceApp.repositories.productRepos.ProductRepository;
import com.commerceApp.commerceApp.repositories.productRepos.ProductReviewRepository;
import com.commerceApp.commerceApp.repositories.userRepos.CustomerRepository;
import com.commerceApp.commerceApp.util.EntityDtoMapping;
import com.commerceApp.commerceApp.util.responseDtos.BaseDto;
import com.commerceApp.commerceApp.util.responseDtos.ErrorDto;
import com.commerceApp.commerceApp.util.responseDtos.ResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.commerceApp.commerceApp.util.EntityDtoMapping.*;

@Service
public class ReviewService {
    @Autowired
    ProductRepository productRepository;
    @Autowired
    ProductReviewRepository productReviewRepository;
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    CustomReviewRepository customReviewRepository;

    public BaseDto addProductReview(ReviewDto reviewDto, String username) {
        BaseDto response;

        Product product = productRepository.findById(reviewDto.getProductId()).get();
        if (product == null)
            return new ErrorDto("Not found", null);

        ProductReview productReview = toProductReview(reviewDto);
        Customer customer = customerRepository.findByEmail(username);

        productReview.setAuthor(customer);
        productReview.setRating(reviewDto.getRating());
        productReview.setReview(reviewDto.getReview());
        productReview.setProduct(product);
        productReviewRepository.save(productReview);


        response = new ResponseDto<>(null, "Success");
        return response;
    }

    public BaseDto getProductReviewByProductId(Long id, String username) {

        Optional<Product> savedProduct = productRepository.findById(id);
        if (!savedProduct.isPresent()) {
            throw new ProductDoesNotExists("Not found");
        }
        List<ProductReview> productReview = productReviewRepository.findByProductId(id);
        if (productReview == null)
            return new ErrorDto("Not found", null);


        List<ReviewDto> reviewDtos = new ArrayList<>();
        productReview.forEach(productReview1 -> {
            ReviewDto reviewDto = toReviewDto(productReview1);
            reviewDtos.add(reviewDto);
        });
        BaseDto response = new ResponseDto<>(null, reviewDtos);
        return response;

    }

    public BaseDto getProductReviewByCustomer( String username) {

        Customer customer=customerRepository.findByEmail(username);
        List<ProductReview> productReview = customReviewRepository.findByCustomerId(customer.getId());
        if (productReview == null)
            return new ErrorDto("Not found", null);
        List<ReviewDto> responseDtos = new ArrayList<>();
        productReview.forEach(productReview1 -> {
            ReviewDto reviewDto = toReviewDto(productReview1);
            responseDtos.add(reviewDto);
        });
        BaseDto response = new ResponseDto<>(null, responseDtos);
        return response;


    }



}
