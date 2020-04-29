package com.commerceApp.commerceApp.services;

import com.commerceApp.commerceApp.models.category.Category;
import com.commerceApp.commerceApp.models.category.CategoryMetadataField;
import com.commerceApp.commerceApp.models.product.Product;
import com.commerceApp.commerceApp.dtos.productDto.ProductvariationSellerDto;
import com.commerceApp.commerceApp.repositories.CategoryFieldRepository;
import com.commerceApp.commerceApp.repositories.CategryMetadataFieldValueRepo;
import com.commerceApp.commerceApp.repositories.ProductRepository;
import com.commerceApp.commerceApp.util.StringToSetParser;
import com.google.common.collect.Sets;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProductVariationService {
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    CategoryFieldRepository categoryFieldRepository;
    @Autowired
    CategryMetadataFieldValueRepo categryMetadataFieldValueRepo;


    public String validateProductVariation(String email, ProductvariationSellerDto productVariationDto) {
        Optional<Product> savedProduct = productRepository.findById(productVariationDto.getProductId());
        String message;

        if (!savedProduct.isPresent()) {
            return "invalid product Id";
        }
        Product parentProduct = savedProduct.get();
        Category category = parentProduct.getCategory();
        Map<String, String> attributes = productVariationDto.getAttributes();

        if (parentProduct.isDeleted()) {
            message = "Parent product does not exist.";
            return message;
        }
        if (!parentProduct.isActive()) {
            message = "Parent product is inactive.";
            return message;
        }
        if (!parentProduct.getSeller().getEmail().equalsIgnoreCase(email)) {
            message = "Parent product does not belong to you.";
            return message;
        }
        if (productVariationDto.getQuantity() <= 0) {
            message = "Quantity should be greater than 0.";
            return message;
        }
        if (productVariationDto.getPrice() <= 0) {
            message = "Price should be greater than 0";
            return message;
        }
return null;

    }
}
