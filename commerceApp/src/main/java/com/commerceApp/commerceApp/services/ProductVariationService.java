package com.commerceApp.commerceApp.services;

import com.commerceApp.commerceApp.dtos.productDto.ProductSellerDto;
import com.commerceApp.commerceApp.models.category.Category;
import com.commerceApp.commerceApp.models.category.CategoryMetadataField;
import com.commerceApp.commerceApp.models.product.Product;
import com.commerceApp.commerceApp.dtos.productDto.ProductvariationSellerDto;
import com.commerceApp.commerceApp.models.product.ProductVariation;
import com.commerceApp.commerceApp.repositories.CategoryFieldRepository;

import com.commerceApp.commerceApp.repositories.CategoryMetadataFieldValueRepo;
import com.commerceApp.commerceApp.repositories.ProductRepository;
import com.commerceApp.commerceApp.repositories.ProductVariationRepository;
import com.commerceApp.commerceApp.util.StringToSetParser;
import com.commerceApp.commerceApp.util.responseDtos.BaseDto;
import com.commerceApp.commerceApp.util.responseDtos.ErrorDto;
import com.commerceApp.commerceApp.util.responseDtos.ResponseDto;
import com.google.common.collect.Sets;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

import static com.commerceApp.commerceApp.util.EntityDtoMapping.*;

@Service
public class ProductVariationService {
    @Autowired
    CategoryService categoryService;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    CategoryFieldRepository categoryFieldRepository;
    @Autowired
    CategoryMetadataFieldValueRepo categoryMetadataFieldValueRepo;
    @Autowired
    ProductVariationRepository productVariationRepository;


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
        List<String> receivedFields = new ArrayList<>(attributes.keySet());
        List<String> actualFields = new ArrayList<>();
        categoryMetadataFieldValueRepo.findAllFieldsOfCategoryById(category.getId())
                .forEach((e) -> {
                    actualFields.add(e[0].toString());
                });

        if (receivedFields.size() < actualFields.size()) {
            message = "Please provide all the fields related to the product category.";
            return message;
        }

        receivedFields.removeAll(actualFields);
        if (!receivedFields.isEmpty()) {
            message = "Invalid fields found in the data.";
            return message;
        }

        List<String> receivedFieldsCopy = new ArrayList<>(attributes.keySet());

        for (String receivedField : receivedFieldsCopy) {

            CategoryMetadataField field = categoryFieldRepository.findByName(receivedField);
            List<Object> savedValues = categoryMetadataFieldValueRepo.findAllValuesOfCategoryField(category.getId(), field.getId());

            String values = savedValues.get(0).toString();
            Set<String> actualValueSet = StringToSetParser.toSetOfValues(values);

            String receivedValues = attributes.get(receivedField);
            Set<String> receivedValueSet = StringToSetParser.toSetOfValues(receivedValues);

            if (!Sets.difference(receivedValueSet, actualValueSet).isEmpty()) {
                message = "Invalid value found for field " + receivedField;
                return message;
            }
        }
        return "success";
    }

    public ResponseEntity<BaseDto> saveNewProductVariation(String email, ProductvariationSellerDto variationDto) {

        BaseDto response;
        String message = validateProductVariation(email, variationDto);

        if (!message.equalsIgnoreCase("success")) {
            response = new ErrorDto("Validation failed", message);
            return new ResponseEntity<BaseDto>(response, HttpStatus.BAD_REQUEST);
        }

        // now we can save the product variation.
        ProductVariation newVariation = toProductVariation(variationDto);
        productVariationRepository.save(newVariation);

        message = "success";
        response = new ResponseDto<>(message, null);
        return new ResponseEntity<BaseDto>(response, HttpStatus.CREATED);
    }
    public ResponseEntity<BaseDto> getProductVariationByIdForSeller(String email, Long id) {
        BaseDto response;
        String message;

        Optional<ProductVariation> savedVariation = productVariationRepository.findById(id);
        if(!savedVariation.isPresent()){
            response = new ErrorDto("Validation failed", "Product variation with id "+id+ " not found");
            return new ResponseEntity<BaseDto>(response, HttpStatus.NOT_FOUND);
        }
        ProductVariation variation = savedVariation.get();
        if(!variation.getProduct().getSeller().getEmail().equalsIgnoreCase(email)){
            message = "Product variation with id "+id+ " does not belong to you.";
            response = new ErrorDto("Validation failed", message);
            return new ResponseEntity<BaseDto>(response, HttpStatus.BAD_REQUEST);
        }
        if(variation.isDeleted()){
            message = "Product Variation does not exist.";
            response = new ErrorDto("Validation failed", message);
            return new ResponseEntity<BaseDto>(response, HttpStatus.BAD_REQUEST);
        }

        ProductvariationSellerDto variationDto = toProductVariationSellerDto(variation);
        ProductSellerDto productDto = toProductSellerDto(variation.getProduct());
        productDto.setCategoryDto(toCategoryDto(variation.getProduct().getCategory()));
        variationDto.setProductDto(productDto);

        response = new ResponseDto<ProductvariationSellerDto>(null,variationDto);
        return new ResponseEntity<BaseDto>(response, HttpStatus.OK);
    }
    


}

