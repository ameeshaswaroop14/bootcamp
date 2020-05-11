package com.commerceApp.commerceApp.services;


//import com.commerceApp.commerceApp.bootloader.Bootstrap;
import com.commerceApp.commerceApp.dtos.categoryDtos.*;
import com.commerceApp.commerceApp.models.category.Category;
import com.commerceApp.commerceApp.models.category.CategoryMetadataField;
import com.commerceApp.commerceApp.models.category.CategoryMetadataFieldValues;
import com.commerceApp.commerceApp.models.product.Product;
import com.commerceApp.commerceApp.models.product.ProductVariation;

import com.commerceApp.commerceApp.repositories.categoryRepos.CategoryFieldRepository;
import com.commerceApp.commerceApp.repositories.categoryRepos.CategoryMetadataFieldValueRepo;
import com.commerceApp.commerceApp.repositories.categoryRepos.CategoryRepository;
import com.commerceApp.commerceApp.repositories.categoryRepos.CategoryRepositoryCustom;
import com.commerceApp.commerceApp.repositories.productRepos.CustomProductRepo;
import com.commerceApp.commerceApp.repositories.productRepos.ProductRepository;
import com.commerceApp.commerceApp.util.*;
import com.commerceApp.commerceApp.util.responseDtos.BaseDto;
import com.commerceApp.commerceApp.util.responseDtos.ErrorDto;
import com.commerceApp.commerceApp.util.responseDtos.ResponseDto;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class CategoryService {

    @Autowired
    ModelMapper modelMapper;
    @Autowired
    CategoryMetadataFieldService categoryMetadataFieldService;
    @Autowired
    CategoryFieldRepository categoryFieldRepository;
    @Autowired
    CategoryMetadataFieldValueRepo categoryMetadataFieldValueRepo;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    MessageSource messageSource;
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    CategoryRepositoryCustom categoryRepositoryCustom;
    @Autowired
    CustomProductRepo customProductRepo;


    public String validateNewCategory(String categoryName, Long parentId) {
        if (parentId == null) {
            Category preStored = categoryRepository.findByName(categoryName);
            if (preStored == null)
                return "valid";
            return "Category already exists.";
        }
        Category parent = categoryRepository.findById(parentId).get();
        // check immediate children
        Set<Category> children = parent.getSubCategories();
        for (Category c : children) {
            if (c.getName().equalsIgnoreCase(categoryName))
                return "Child category exists with same name.";
        }
        // check product associations
        Set<Product> products = parent.getProducts();
        if (!products.isEmpty())
            return "Parent category has product associations.";

        // check path from parent to root
        while (parent != null) {
            if (parent.getName().equalsIgnoreCase(categoryName))
                return "Category already exists as ancestor.";
            parent = parent.getParentCategory();
        }

        return "valid";
    }

    public ResponseEntity<BaseDto> createNewCategory(String categoryName, Long parentId) {
        String message = validateNewCategory(categoryName, parentId);
        BaseDto response;
        if (!message.equals("valid")) {
            response = new ErrorDto("Validation failed", "Try again");
            return new ResponseEntity<BaseDto>(response, HttpStatus.BAD_REQUEST);
        }

        Category category = new Category(categoryName);
        Category parent;
        if (parentId == null) {
            category.setParentCategory(null);
            categoryRepository.save(category);
        } else {
            Optional<Category> parentCategory = categoryRepository.findById(parentId);
            if (!parentCategory.isPresent()) {
                response = new ErrorDto("Validation Failed", "Parent Category Id does not exists");
                return new ResponseEntity<BaseDto>(response, HttpStatus.CONFLICT);
            } else {
                parent = parentCategory.get();
                category.setParentCategory(parent);
                parent.addSubCategory(category);
                categoryRepository.save(parent);
            }
        }
        response = new ResponseDto<>("Success", null);
        return new ResponseEntity<BaseDto>(response, HttpStatus.CREATED);

    }

    CategoryDto toCategoryDtoNonRecursive(Category category) {
        if (category == null)
            return null;
        CategoryDto categoryDto = modelMapper.map(category, CategoryDto.class);
        categoryDto.setParent(null);
        return categoryDto;
    }

    public CategoryAdminResponseDto toCategoryAdminResponse(Category category) {
        CategoryAdminResponseDto categoryAdminResponseDto = new CategoryAdminResponseDto();
        CategoryDto categoryDto = EntityDtoMapping.toCategoryDto(category);
        categoryAdminResponseDto.setCategory(categoryDto);
        //for child
        Set<CategoryDto> subCategories;
        if (category.getSubCategories() != null) {
            subCategories = new HashSet<>();
            category.getSubCategories().forEach((e) -> {
                subCategories.add(toCategoryDtoNonRecursive(e));
            });
            categoryAdminResponseDto.setSubCategories(subCategories);
        }
        //get all metadata fields
        Set<CategoryMetadataFieldDto> categoryMetadataFieldDtos;
        if (category.getFieldValues() == null) {
            categoryMetadataFieldDtos = new HashSet<>();
            category.getFieldValues().forEach((e) ->
            {
                CategoryMetadataFieldDto dto = categoryMetadataFieldService.toCategoryMetadataFieldDto(e.getCategoryMetadataField());
                dto.setValues(StringToSetParser.toSetOfValues(e.getValue()));

                categoryMetadataFieldDtos.add(dto);
            });
            categoryAdminResponseDto.setFieldValues(categoryMetadataFieldDtos);

        }
        return categoryAdminResponseDto;
    }

    public ResponseEntity<BaseDto> getCategory(Long categoryId) {
        BaseDto response;
        Optional<Category> pre = categoryRepository.findById(categoryId);
        if (!pre.isPresent()) {
            response = new ErrorDto("Not found", "Category with the given id does not exist");
            return new ResponseEntity<BaseDto>(response, HttpStatus.NOT_FOUND);
        }

        CategoryAdminResponseDto categoryAdminResponseDto = toCategoryAdminResponse(pre.get());

        response = new ResponseDto<>(null, categoryAdminResponseDto);
        return new ResponseEntity<BaseDto>(response, HttpStatus.OK);
    }

    public ResponseEntity<BaseDto> getAllCategories(String offset, String size, String sortByField) {
        Integer pageNo = Integer.parseInt(offset);
        Integer pageSize = Integer.parseInt(size);

        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortByField).ascending());
        List<Category> categories = categoryRepository.findAll(pageable);
        List<CategoryAdminResponseDto> categoryDtos = new ArrayList<>();

        categories.forEach((category) -> {
            categoryDtos.add(toCategoryAdminResponse(category));
        });
        BaseDto response;
        response = new ResponseDto<>(null, categoryDtos);
        return new ResponseEntity<BaseDto>(response, HttpStatus.OK);
    }

    public ResponseEntity<BaseDto> getAllCategoriesForSeller() {
        BaseDto response;
        List<Category> categories = categoryRepository.findAll();
        List<CategoryAdminResponseDto> categoryDtos = new ArrayList<>();

        categories.forEach((category) -> {
            categoryDtos.add(toCategoryAdminResponse(category));
        });

        response = new ResponseDto<>(null, categoryDtos);
        return new ResponseEntity<BaseDto>(response, HttpStatus.OK);
    }

    public ResponseEntity<BaseDto> deleteCategoryById(Long id) {
        BaseDto response;
        Optional<Category> savedCategory = categoryRepository.findById(id);
        if (!savedCategory.isPresent()) {
            response = new ErrorDto("Not found", "Category does not exist");
            return new ResponseEntity<BaseDto>(response, HttpStatus.NOT_FOUND);
        }

        Category category = savedCategory.get();

        if (!category.getProducts().isEmpty()) {
            response = new ErrorDto("Validation failed", "This category is associated with other products");
            return new ResponseEntity<BaseDto>(response, HttpStatus.CONFLICT);
        }

        if (!category.getSubCategories().isEmpty()) {
            response = new ErrorDto("Validation failed", "This category has child categories associated");
            return new ResponseEntity<BaseDto>(response, HttpStatus.CONFLICT);
        }
        categoryRepositoryCustom.deleteCategoryById(id);

        response = new ResponseDto<>("Successfully deleted", null);
        return new ResponseEntity<BaseDto>(response, HttpStatus.OK);
    }

    public ResponseEntity<BaseDto> updateCategory(Long id, String name) {
        BaseDto response;
        Optional<Category> savedCategory = categoryRepository.findById(id);
        if (!savedCategory.isPresent()) {
            response = new ErrorDto("Not found", "Category does not exists");
            return new ResponseEntity<BaseDto>(response, HttpStatus.NOT_FOUND);
        }

        Category category = savedCategory.get();
        category.setName(name);
        categoryRepository.save(category);

        response = new ResponseDto<>("Successfully updated", null);
        return new ResponseEntity<BaseDto>(response, HttpStatus.OK);
    }

    public ResponseEntity<BaseDto> getAllCategoriesForCustomer(Long id) {
        BaseDto response;
        if (id == null) {
            List<Category> rootCategories = categoryRepository.findByParentIdIsNull();
            List<CategoryDto> categoryDtos = new ArrayList<>();
            rootCategories.forEach((e) -> {
                categoryDtos.add(toCategoryDtoNonRecursive(e));
            });
            response = new ResponseDto<>("Success", categoryDtos);
            return new ResponseEntity<BaseDto>(response, HttpStatus.OK);
        }
        Optional<Category> savedCategory = categoryRepository.findById(id);
        if (!savedCategory.isPresent()) {
            response = new ErrorDto("Not Found", "Category does not exists");
            return new ResponseEntity<BaseDto>(response, HttpStatus.NOT_FOUND);
        }

        Category category = savedCategory.get();
        Set<Category> subCategories = category.getSubCategories();
        List<CategoryDto> subCategoryDtos = new ArrayList<>();

        subCategories.forEach((e) -> {
            subCategoryDtos.add(toCategoryDtoNonRecursive(e));
        });
        response = new ResponseDto<>("success", subCategoryDtos);
        return new ResponseEntity<BaseDto>(response, HttpStatus.OK);
    }

    public String validateMetadataFieldValues(CategoryMetadataFieldValuesDto categoryMetadataFieldValuesDto) {
        Optional<Category> savedCategory = categoryRepository.findById(categoryMetadataFieldValuesDto.getCategoryId());
        String message;
        if (!savedCategory.isPresent()) {
            message = "Category does not exist.";
            return message;
        }
        Category category = savedCategory.get();
        for (CategoryMetadataFieldDto preValues : categoryMetadataFieldValuesDto.getFieldValues()) {
            Optional<CategoryMetadataField> field = categoryFieldRepository.findById(preValues.getId());
            if (!field.isPresent()) {
                message = "Field does not exist";
                return message;
            }/*
            if (preValues.getValues().isEmpty()) {
                message = "No field values provided to insert for field id ";
                return message;
            }
            */

        }
        message = "success";
        return message;
    }

    public ResponseEntity<BaseDto> createCategoryMetadataFieldValues(CategoryMetadataFieldValuesDto categoryMetadataFieldValuesDto) {
       /* String message = validateMetadataFieldValues(categoryMetadataFieldValuesDto);
        BaseDto response;
        if (!message.equalsIgnoreCase("success")) {
            response = new ErrorDto("Validation failed", message);
            return new ResponseEntity<BaseDto>(response, HttpStatus.BAD_REQUEST);
        }

        */


        BaseDto response;
        Category category = categoryRepository.findById(categoryMetadataFieldValuesDto.getCategoryId()).get();
        CategoryMetadataFieldValues categoryFieldValues = new CategoryMetadataFieldValues();
        CategoryMetadataField categoryField;

        for (CategoryMetadataFieldDto fieldValuePair : categoryMetadataFieldValuesDto.getFieldValues()) {

            categoryField = categoryFieldRepository.findById(fieldValuePair.getId()).get();
            String values = StringToSetParser.toCommaSeparatedString(fieldValuePair.getValues());

            categoryFieldValues.setValue(values);
            categoryFieldValues.setCategory(category);
            categoryFieldValues.setCategoryMetadataField(categoryField);
            categoryMetadataFieldValueRepo.save(categoryFieldValues);

        }
        response = new ResponseDto<>("Success", null);
        return new ResponseEntity<BaseDto>(response, HttpStatus.CREATED);
    } public ResponseEntity<BaseDto> getFilteringDetailsForCategory(Long categoryId) {
        BaseDto response;

        Optional<Category> savedCategory = categoryRepository.findById(categoryId);
        if (!savedCategory.isPresent()) {
            response = new ErrorDto("Not Found", "Category with id " + categoryId + " does not exist.");
            return new ResponseEntity<BaseDto>(response, HttpStatus.NOT_FOUND);
        }

        Category category = savedCategory.get();
        CategoryFilteringDetailsDto filterDto = new CategoryFilteringDetailsDto();

        PricePair pair = new PricePair();
        pair.minPrice = Double.POSITIVE_INFINITY;
        pair.maxPrice = 0d;
        getMinMaxPriceForCategory(category, pair);

        Map<String, String> map = new HashMap<>();
        getAllFieldValuePairsForCategory(category, map);

        Map<String, Set<String>> fieldValueMap = new HashMap<>();
        map.forEach((field, value) -> {
            fieldValueMap.put(field, StringToSetParser.toSetOfValues(value));
        });

        filterDto.setBrands(getAllBrandsForCategory(categoryId));
        filterDto.setMaxPrice(pair.maxPrice);
        filterDto.setMinPrice(pair.minPrice);
        filterDto.setFieldValues(fieldValueMap);

        response = new ResponseDto<CategoryFilteringDetailsDto>(null, filterDto);
        return new ResponseEntity<BaseDto>(response, HttpStatus.OK);

    }
    public Set<String> getAllBrandsForCategory(Long categoryId) {
        Optional<Category> savedCategory = categoryRepository.findById(categoryId);
        if (!savedCategory.isPresent())
            return null;

        Set<String> brands = new HashSet<>();
        Category category = savedCategory.get();

        if (category.getSubCategories() == null || category.getSubCategories().isEmpty()) {
            brands.addAll(productRepository.findAllBrandsByCategoryId(categoryId));
        } else {
            category.getSubCategories().forEach(child -> {
                brands.addAll(getAllBrandsForCategory(child.getId()));
            });
        }
        return brands;
    }
    public void getAllFieldValuePairsForCategory(Category category, Map<String, String> fieldValueMap) {

        // assume that the category exists. check before calling the method.

        if (category.getSubCategories() != null && !category.getSubCategories().isEmpty()) {

            Set<Category> subCategories = category.getSubCategories();
            for (Category child : subCategories) {
                getAllFieldValuePairsForCategory(child, fieldValueMap);
            }
        } else {
            List<Object[]> fieldValuePairs =
                    categoryMetadataFieldValueRepo.findAllFieldsAndValuesForLeafCategory(category.getId());

            Set<String> fields = fieldValueMap.keySet();

            for (Object[] pair : fieldValuePairs) {
                if (fields.contains(pair[0].toString())) {
                    String values = fieldValueMap.get(pair[0].toString());
                    values = values + "," + pair[1];
                    fieldValueMap.put(pair[0].toString(), values);
                } else {
                    fieldValueMap.put(pair[0].toString(), pair[1].toString());
                }
            }
        }
    }



    public void getMinMaxPriceForCategory(Category category, PricePair pair) {

        if (category.getSubCategories() == null || category.getSubCategories().isEmpty()) {


            if (category.getProducts() != null && category.getProducts().isEmpty() == false) {
                Set<Product> products = category.getProducts();
                for (Product product : products) {


                    if (product.getVariations() != null && product.getVariations().isEmpty() == false) {
                        Set<ProductVariation> variations = product.getVariations();

                        for (ProductVariation variation : variations) {
                            if (variation.getPrice() < pair.minPrice) {
                                pair.minPrice = variation.getPrice();
                                System.out.println("minPrice found" + pair.minPrice);
                            }

                            if (variation.getPrice() > pair.maxPrice) {
                                pair.maxPrice = variation.getPrice();
                                System.out.println("maxPrice found" + pair.maxPrice);
                            }
                        }
                    }
                }
            }
        } else {

            Set<Category> subCategories = category.getSubCategories();
            for (Category subCategory : subCategories) {
                getMinMaxPriceForCategory(subCategory, pair);
            }
        }

    }
}