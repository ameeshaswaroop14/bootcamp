package com.commerceApp.commerceApp.services;

import com.commerceApp.commerceApp.dtos.productDto.*;
import com.commerceApp.commerceApp.models.Seller;
import com.commerceApp.commerceApp.models.category.Category;
import com.commerceApp.commerceApp.models.category.CategoryMetadataField;
import com.commerceApp.commerceApp.models.product.Product;
import com.commerceApp.commerceApp.models.product.ProductVariation;

import com.commerceApp.commerceApp.repositories.categoryRepos.CategoryFieldRepository;
import com.commerceApp.commerceApp.repositories.categoryRepos.CategoryMetadataFieldValueRepo;
import com.commerceApp.commerceApp.repositories.categoryRepos.CategoryRepository;
import com.commerceApp.commerceApp.repositories.categoryRepos.CustomCategoryMetadataFieldValueRepo;
import com.commerceApp.commerceApp.repositories.productRepos.ProductRepository;
import com.commerceApp.commerceApp.repositories.productRepos.ProductVariationRepository;
import com.commerceApp.commerceApp.repositories.userRepos.SellerRepository;
import com.commerceApp.commerceApp.util.StringToSetParser;
import com.commerceApp.commerceApp.util.responseDtos.BaseDto;
import com.commerceApp.commerceApp.util.responseDtos.ErrorDto;
import com.commerceApp.commerceApp.util.responseDtos.ResponseDto;
import com.google.common.collect.Sets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    CustomCategoryMetadataFieldValueRepo customCategoryMetadataFieldValueRepo;
    @Autowired
    SellerRepository sellerRepository;
    @Autowired
    CurrentUserService currentUserService;

    public ResponseEntity<BaseDto> saveNewProductVariation(String email, ProductvariationSellerDto variationDto) {

        BaseDto response;
        String message = validateNewProductVariation(email, variationDto);

        if (!message.equalsIgnoreCase("success")) {
            response = new ErrorDto("Validation failed", message);
            return new ResponseEntity<BaseDto>(response, HttpStatus.BAD_REQUEST);
        }

        // now we can save the product variation.
        ProductVariation newVariation = toProductVariation(variationDto);
        productVariationRepository.save(newVariation);

        message = "success";
        response = new ResponseDto<>(null, message);
        return new ResponseEntity<BaseDto>(response, HttpStatus.CREATED);
    }

    public String validateNewProductVariation(String email, ProductvariationSellerDto variationDto) {
        BaseDto response;
        String message;

        Optional<Product> savedProduct = productRepository.findById(variationDto.getProductId());
        if (!savedProduct.isPresent()) {
            message = "Parent product does not exist.";
            return message;
        }

        Product parentProduct = savedProduct.get();
        Category category = parentProduct.getCategory();
        Map<String, String> attributes = variationDto.getAttributes();


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
        if (variationDto.getQuantity() <= 0) {
            message = "Quantity should be greater than 0.";
            return message;
        }
        if (variationDto.getPrice() <= 0) {
            message = "Price should be greater than 0";
            return message;
        }
        /*

        // check if all the fields are actually related to the product category.
       List<String> receivedFields = new ArrayList<>(attributes.keySet());
        List<String> actualFields = new ArrayList<>();
        customCategoryMetadataFieldValueRepo.findAllFieldsOfCategoryById(category.getId())
                .forEach((categoryMetadataField) -> {
                    actualFields.add(categoryMetadataField[0].toString());
                //});

        if (receivedFields.size() < actualFields.size()) {
            message = "Please provide all the fields related to the product category.";
            return message;
        }

        receivedFields.removeAll(actualFields);
        if (!receivedFields.isEmpty()) {
            message = "Invalid fields found in the data.";
            return message;
        }

        // check validity of values of fields.
        List<String> receivedFieldsCopy = new ArrayList<>(attributes.keySet());


       */

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




    public ResponseEntity<BaseDto> getProductVariationByIdForSeller(String email, Long id) {
        BaseDto response;
        String message;

        Optional<ProductVariation> savedVariation = productVariationRepository.findById(id);
        if (!savedVariation.isPresent()) {
            response = new ErrorDto("Validation failed", "Product variation with id " + id + " not found");
            return new ResponseEntity<BaseDto>(response, HttpStatus.NOT_FOUND);
        }
        ProductVariation variation = savedVariation.get();
        if (!variation.getProduct().getSeller().getEmail().equalsIgnoreCase(email)) {
            message = "Product variation with id " + id + " does not belong to you.";
            response = new ErrorDto("Validation failed", message);
            return new ResponseEntity<BaseDto>(response, HttpStatus.BAD_REQUEST);
        }
        if (variation.isDeleted()) {
            message = "Product Variation does not exist.";
            response = new ErrorDto("Validation failed", message);
            return new ResponseEntity<BaseDto>(response, HttpStatus.BAD_REQUEST);
        }

        ProductvariationSellerDto variationDto = toProductVariationSellerDto(variation);
        ProductSellerDto productDto = toProductSellerDto(variation.getProduct());
        productDto.setCategoryDto(toCategoryDto(variation.getProduct().getCategory()));
        variationDto.setProductDto(productDto);

        response = new ResponseDto<ProductvariationSellerDto>(null, variationDto);
        return new ResponseEntity<BaseDto>(response, HttpStatus.OK);
    }

    public ResponseEntity<BaseDto> getAllProductVariationsByProductIdForSeller(String email, Long id, String offset, String size, String sortByField, String order) {
        BaseDto response;
        String message;

        Optional<Product> savedProduct = productRepository.findById(id);
        if (!savedProduct.isPresent()) {
            response = new ErrorDto("Validation failed", "Product with id not found");
            return new ResponseEntity<BaseDto>(response, HttpStatus.NOT_FOUND);
        }
        Product product = savedProduct.get();
        if (!product.getSeller().getEmail().equalsIgnoreCase(email)) {
            message = "Product with id  does not belong to you.";
            response = new ErrorDto("Validation failed", message);
            return new ResponseEntity<BaseDto>(response, HttpStatus.BAD_REQUEST);
        }
        if (product == null) {
            message = "Product does not exist.";
            response = new ErrorDto("Validation failed", message);
            return new ResponseEntity<BaseDto>(response, HttpStatus.BAD_REQUEST);
        }

        Integer pageNo = Integer.parseInt(offset);
        Integer pageSize = Integer.parseInt(size);

        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortByField).ascending());
        List<ProductVariation> variations;
        variations = productVariationRepository.findByProductId(id, pageable);

        List<ProductvariationSellerDto> variationDtos = new ArrayList<>();
        variations.forEach(variation -> {
            ProductvariationSellerDto variationDto = toProductVariationSellerDto(variation);
            ProductSellerDto productDto = toProductSellerDto(variation.getProduct());
            productDto.setCategoryDto(toCategoryDto(variation.getProduct().getCategory()));
            variationDto.setProductDto(productDto);
            variationDtos.add(variationDto);
        });

        response = new ResponseDto<>(null, variationDtos);
        return new ResponseEntity<BaseDto>(response, HttpStatus.OK);
    }

    public ProductCustomerDto getProductCustomerViewDto(Product product) {
        ProductSellerDto productDto = toProductSellerDto(product);
        productDto.setCategoryDto(toCategoryDto(product.getCategory()));

        Set<ProductvariationSellerDto> variationDtos = new HashSet<>();
        product.getVariations().forEach((variation -> {
            ProductvariationSellerDto variationDto = toProductVariationSellerDto(variation);
            variationDto.setProductDto(null);
            variationDtos.add(variationDto);
        }));

        ProductCustomerDto productCustomerViewDto = new ProductCustomerDto();

        productCustomerViewDto.setVariations(variationDtos);

        return productCustomerViewDto;
    }

    public Set<ProductCustomerDto> getAllProductCustomerViewDtosByCategory(Long categoryId, Pageable pageable) {
        Set<ProductCustomerDto> productCustomerViewDtos = new LinkedHashSet<>();

        Category category = categoryRepository.findById(categoryId).get();

        if (category.getSubCategories() == null || category.getSubCategories().isEmpty()) {
            List<Product> products = productRepository.findByCategoryId(categoryId, pageable);
            for (Product product : products) {
                productCustomerViewDtos.add(getProductCustomerViewDto(product));
            }
        } else {
            for (Category child : category.getSubCategories()) {
                productCustomerViewDtos.addAll(getAllProductCustomerViewDtosByCategory(child.getId(), pageable));
            }
        }
        return productCustomerViewDtos;
    }


    public ResponseEntity<BaseDto> getAllSimilarProductsByProductId(Long id, String offset, String size, String sortByField, String order) {
        BaseDto response;
        String message;

        Optional<Product> savedProduct = productRepository.findById(id);
        if (!savedProduct.isPresent()) {
            message = "Product with id  not found";
            response = new ErrorDto("Validation failed", message);
            return new ResponseEntity<BaseDto>(response, HttpStatus.NOT_FOUND);
        }

        Product product = savedProduct.get();

        if (product.isDeleted()) {
            message = "Product with id " + id + " not found";
            response = new ErrorDto("Validation failed", message);
            return new ResponseEntity<BaseDto>(response, HttpStatus.NOT_FOUND);
        }
        if (!product.isActive()) {
            message = "Product is inactive.";
            response = new ErrorDto("Validation failed", message);
            return new ResponseEntity<BaseDto>(response, HttpStatus.BAD_REQUEST);
        }

        Category category = product.getCategory();

        Integer pageNo = Integer.parseInt(offset);
        Integer pageSize = Integer.parseInt(size);

        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortByField).ascending());

        Set<ProductCustomerDto> similarProducts = getAllProductCustomerViewDtosByCategory(category.getId(), pageable);
        similarProducts.remove(getProductCustomerViewDto(product));

        if (similarProducts.isEmpty()) {
            message = "No similar products found.";
            response = new ResponseDto<Set>(message, similarProducts);
            return new ResponseEntity<BaseDto>(response, HttpStatus.OK);
        }

        response = new ResponseDto<Set>(null, similarProducts);
        return new ResponseEntity<BaseDto>(response, HttpStatus.OK);
    }

    public ResponseEntity<BaseDto> validateProductVariationUpdate(Long id, String email, ProductVariationUpdateDto variationDto) {
        BaseDto response;
        String message;

        Optional<ProductVariation> savedVariation = productVariationRepository.findById(id);
        if (!savedVariation.isPresent()) {
            message = "Product variation with id " + id + " not found";
            response = new ErrorDto("Validation failed", message);
            return new ResponseEntity<BaseDto>(response, HttpStatus.NOT_FOUND);
        }

        ProductVariation variation = savedVariation.get();

        if (variation.isDeleted()) {
            message = "Product variation does not exist.";
            response = new ErrorDto("Validation failed", message);
            return new ResponseEntity<BaseDto>(response, HttpStatus.NOT_FOUND);
        }
        if (!variation.getProduct().isActive()) {
            message = "Parent product is inactive.";
            response = new ErrorDto("Validation failed", message);
            return new ResponseEntity<BaseDto>(response, HttpStatus.BAD_REQUEST);
        }
        if (!variation.getProduct().getSeller().getEmail().equalsIgnoreCase(email)) {
            message = "Product variation does not belong to you.";
            response = new ErrorDto("Validation failed", message);
            return new ResponseEntity<BaseDto>(response, HttpStatus.BAD_REQUEST);

        }
        if (variationDto.getQuantityAvailable() != null && variationDto.getQuantityAvailable() <= 0) {
            message = "Quantity should be greater than 0.";
            response = new ErrorDto("Validation failed", message);
            return new ResponseEntity<BaseDto>(response, HttpStatus.BAD_REQUEST);
        }
        if (variationDto.getPrice() != null && variationDto.getPrice() <= 0) {
            message = "Price should be greater than 0";
            response = new ErrorDto("Validation failed", message);
            return new ResponseEntity<BaseDto>(response, HttpStatus.BAD_REQUEST);
        }

      /*  // check if all the fields are actually related to the product category.
        Map<String, String> attributes = variationDto.getAttributes();
        if(attributes!=null){
            Category category = variation.getProduct().getCategory();
            List<String> receivedFields = new ArrayList<>(attributes.keySet());
            List<String> actualFields = new ArrayList<>();
           customCategoryMetadataFieldValueRepo .findAllFieldsOfCategoryById(category.getId())
                    .forEach((e)->{
                        actualFields.add(e[0].toString());
                    });

            receivedFields.removeAll(actualFields);
            if(!receivedFields.isEmpty()){
                message = "Invalid fields found in the data.";
                response = new ErrorDto("Validation failed", message);
                return new ResponseEntity<BaseDto>(response, HttpStatus.BAD_REQUEST);
            }

            // check validity of values of fields.

       */
        Map<String, String> attributes = variationDto.getAttributes();
        if (attributes != null) {
            Category category = variation.getProduct().getCategory();
            List<String> receivedFields = new ArrayList<>(attributes.keySet());
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
                    response = new ErrorDto("Validation failed", message);
                    return new ResponseEntity<BaseDto>(response, HttpStatus.BAD_REQUEST);
                }
            }

        }
        return null;
    }


    public ResponseEntity<BaseDto> updateProductVariationById(Long id, String email, ProductVariationUpdateDto variationDto) {
        BaseDto response;
        String message;

        ResponseEntity<BaseDto> validationResponse = validateProductVariationUpdate(id, email, variationDto);
        if (validationResponse != null)
            return validationResponse;

        ProductVariation variation = productVariationRepository.findById(id).get();

        applyProductVariationUpdateDtoToProductVariation(variation, variationDto);
        productVariationRepository.save(variation);

        message = "success";
        response = new ResponseDto<>(null, message);
        return new ResponseEntity<BaseDto>(response, HttpStatus.OK);

    }

    private void applyProductVariationUpdateDtoToProductVariation(ProductVariation variation, ProductVariationUpdateDto variationDto) {

        if (variationDto.getQuantityAvailable() != null)
            variation.setQuantityAvailable(variationDto.getQuantityAvailable());

        if (variationDto.getPrice() != null)
            variation.setPrice(variationDto.getPrice());

        if (variationDto.getActive() != null)
            variation.setActive(variationDto.getActive());

        if (variationDto.getAttributes() != null) {
            Map<String, String> newAttributes = variationDto.getAttributes();
            if (!newAttributes.isEmpty()) {
                Map<String, String> oldAttributes = variation.getProductAttributes();

                for (String key : newAttributes.keySet()) {
                    String newValue = newAttributes.get(key);
                    oldAttributes.put(key, newValue);
                }
            }
        }
    }


}

