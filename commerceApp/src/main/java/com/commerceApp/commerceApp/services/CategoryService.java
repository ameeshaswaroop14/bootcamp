package com.commerceApp.commerceApp.services;

import com.commerceApp.commerceApp.Models.Category;
import com.commerceApp.commerceApp.Models.CategoryMetadataField;
import com.commerceApp.commerceApp.Models.Product;
import com.commerceApp.commerceApp.dtos.CategoryDto;
import com.commerceApp.commerceApp.repositories.CategoryFieldRepository;
import com.commerceApp.commerceApp.repositories.CategoryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class CategoryService {
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    ModelMapper modelMapper;

    CategoryDto toCategoryDto(Category category){
        if(category == null)
            return null;
        CategoryDto categoryDto = modelMapper.map(category, CategoryDto.class);
        CategoryDto parentDto = toCategoryDto(category.getParentCategory());
        categoryDto.setParent(parentDto);
        return categoryDto;
    }

    public String validateNewCategory(String categoryName, Long parentId){
        String message;
        if(parentId == null){
            Category preStored = categoryRepository.findByName(categoryName);
            if(preStored == null)
                return "valid";
            return "Category already exists.";
        }
            Category parent = categoryRepository.findById(parentId).get();
            // check immediate children
            Set<Category> children = parent.getSubCategories();
            for(Category c : children){
                if(c.getName().equalsIgnoreCase(categoryName))
                    return "Child category exists with same name.";
            }
            // check product associations of parent
            Set<Product> products = parent.getProducts();
            if(!products.isEmpty())
                return "Parent category has product associations.";

            // check path from parent to root
            while(parent!=null){
                if(parent.getName().equalsIgnoreCase(categoryName))
                    return "Category already exists as ancestor.";
                parent = parent.getParentCategory();
            }

            return "valid";
        }
    public ResponseEntity<String> createNewCategory(String categoryName, Long parentId) {
        String message = validateNewCategory(categoryName, parentId);
        if(!message.equals("valid")){
            return new ResponseEntity<>("Validation Failed", HttpStatus.BAD_REQUEST);
        }

        Category category = new Category(categoryName);
        Category parent;
        if(parentId==null){
            category.setParentCategory(null);
            categoryRepository.save(category);
        }
        else{
            Optional<Category> parentCategory = categoryRepository.findById(parentId);
            if(!parentCategory.isPresent()){
                return new ResponseEntity<>("Parent Category not found", HttpStatus.CONFLICT);
            }
            else{
                parent = parentCategory.get();
                category.setParentCategory(parent);
                parent.addSubCategory(category);
                categoryRepository.save(parent);
            }
        }
        return new ResponseEntity<>("Success", HttpStatus.CREATED);
    }










}
