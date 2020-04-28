package com.commerceApp.commerceApp.services;

import com.commerceApp.commerceApp.dtos.categoryDtos.CategoryMetadataFieldPairDto;
import com.commerceApp.commerceApp.models.category.Category;
import com.commerceApp.commerceApp.models.category.CategoryMetadataField;
import com.commerceApp.commerceApp.models.product.Product;
import com.commerceApp.commerceApp.dtos.categoryDtos.CategoryAdminResponseDto;
import com.commerceApp.commerceApp.dtos.categoryDtos.CategoryDto;
import com.commerceApp.commerceApp.dtos.categoryDtos.CategoryMetadataFieldDto;
import com.commerceApp.commerceApp.repositories.CategoryFieldRepository;
import com.commerceApp.commerceApp.repositories.CategoryRepository;
import com.commerceApp.commerceApp.util.*;
import com.commerceApp.commerceApp.util.responseDtos.BaseDto;
import com.commerceApp.commerceApp.util.responseDtos.ErrorDto;
import com.commerceApp.commerceApp.util.responseDtos.ResponseDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
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
    CategoryRepository categoryRepository;
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    CategoryMetadataFieldService categoryMetadataFieldService;
    @Autowired
    CategoryFieldRepository categoryFieldRepository;



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
            response=new ErrorDto("Validation failed","Try again");
            return new ResponseEntity<BaseDto>(response,HttpStatus.BAD_REQUEST);
        }

        Category category = new Category(categoryName);
        Category parent;
        if (parentId == null) {
            category.setParentCategory(null);
            categoryRepository.save(category);
        } else {
            Optional<Category> parentCategory = categoryRepository.findById(parentId);
            if (!parentCategory.isPresent()) {
                response=new ErrorDto("Validation Failed","Parent Category Id does not exists");
                return new ResponseEntity<BaseDto>(response,HttpStatus.CONFLICT);
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
    public ResponseEntity<BaseDto>  getCategory(Long categoryId){
        BaseDto response;
        Optional<Category> pre=categoryRepository.findById(categoryId);
        if(!pre.isPresent()){
            response=new ErrorDto("Not found","Category with the given id does not exist");
            return new ResponseEntity<BaseDto>(response,HttpStatus.NOT_FOUND);
        }

        CategoryAdminResponseDto categoryAdminResponseDto = toCategoryAdminResponse(pre.get());

        response = new ResponseDto<>(null,categoryAdminResponseDto);
        return new ResponseEntity<BaseDto>(response, HttpStatus.OK);
    }
    public ResponseEntity<BaseDto> getAllCategories(String offset, String size, String sortByField){
        Integer pageNo = Integer.parseInt(offset);
        Integer pageSize = Integer.parseInt(size);

        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortByField).ascending());
        List<Category> categories = categoryRepository.findAll(pageable);
        List<CategoryAdminResponseDto> categoryDtos = new ArrayList<>();

        categories.forEach((category)->{
            categoryDtos.add(toCategoryAdminResponse(category));
        });
        BaseDto response;
        response = new ResponseDto<>(null,categoryDtos);
        return new ResponseEntity<BaseDto>(response, HttpStatus.OK);
    }

    public ResponseEntity<BaseDto> getAllCategoriesForSeller() {
        BaseDto response;
        List<Category> categories = categoryRepository.findAll();
        List<CategoryAdminResponseDto> categoryDtos = new ArrayList<>();

        categories.forEach((category)->{
            categoryDtos.add(toCategoryAdminResponse(category));
        });

        response = new ResponseDto<>(null,categoryDtos);
        return new ResponseEntity<BaseDto>(response, HttpStatus.OK);
    }
    public ResponseEntity<BaseDto> deleteCategoryById(Long id) {
        BaseDto response;
        Optional<Category> savedCategory = categoryRepository.findById(id);
        if(!savedCategory.isPresent()){
            response = new ErrorDto("Not found","Category does not exist");
            return new ResponseEntity<BaseDto>(response, HttpStatus.NOT_FOUND);
        }

        Category category = savedCategory.get();

        if(!category.getProducts().isEmpty()){
            response = new ErrorDto("Validation failed","This category is associated with other products");
            return new ResponseEntity<BaseDto>(response, HttpStatus.CONFLICT);
        }

        if(!category.getSubCategories().isEmpty()){
            response =new ErrorDto("Validation failed","This category has child categories associated");
            return new ResponseEntity<BaseDto>(response, HttpStatus.CONFLICT);
        }
        categoryRepository.deleteCategoryById(id);

        response = new ResponseDto<>("Successfully deleted",null);
        return new ResponseEntity<BaseDto>(response, HttpStatus.OK);
    }
    public ResponseEntity<BaseDto> updateCategory(Long id, String name) {
        BaseDto response;
        Optional<Category> savedCategory = categoryRepository.findById(id);
        if(!savedCategory.isPresent()){
            response = new ErrorDto("Not found","Category does not exists");
            return new ResponseEntity<BaseDto>(response, HttpStatus.NOT_FOUND);
        }

        Category category = savedCategory.get();
        category.setName(name);
        categoryRepository.save(category);

        response = new ResponseDto<>("Successfully updated",null);
        return new ResponseEntity<BaseDto>(response, HttpStatus.OK);
    }
    public ResponseEntity<BaseDto> getAllCategoriesForCustomer(Long id) {
        BaseDto response;
        if(id==null) {
            List<Category> rootCategories = categoryRepository.findByParentIdIsNull();
            List<CategoryDto> categoryDtos = new ArrayList<>();
            rootCategories.forEach((e) -> {
                categoryDtos.add(toCategoryDtoNonRecursive(e));
            });
            response = new ResponseDto<>("Success",categoryDtos);
            return new ResponseEntity<BaseDto>(response, HttpStatus.OK);
        }
        Optional<Category> savedCategory = categoryRepository.findById(id);
        if(!savedCategory.isPresent()){
            response = new ErrorDto("Not Found","Category does not exists");
            return new ResponseEntity<BaseDto>(response, HttpStatus.NOT_FOUND);
        }

        Category category = savedCategory.get();
        Set<Category> subCategories = category.getSubCategories();
        List<CategoryDto> subCategoryDtos = new ArrayList<>();

        subCategories.forEach((e)->{
            subCategoryDtos.add(toCategoryDtoNonRecursive(e));
        });
        response = new ResponseDto<>("success",subCategoryDtos);
        return new ResponseEntity<BaseDto>(response, HttpStatus.OK);
    }
   public  String validateMetadataFieldcategory(CategoryMetadataFieldPairDto categoryMetadataFieldPairDto){
        Optional<Category> category=categoryRepository.findById(categoryMetadataFieldPairDto.getCategoryId());
        String message;
        if(category==null)
            message="Category id invalid";
      //  Optional<CategoryMetadataField> categoryMetadataField=categoryFieldRepository.findById(categoryMetadataFieldPairDto.g)
           return null;
   }




}

















