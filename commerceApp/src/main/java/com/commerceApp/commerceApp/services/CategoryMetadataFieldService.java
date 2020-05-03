package com.commerceApp.commerceApp.services;

import com.commerceApp.commerceApp.models.AuditInformation;
import com.commerceApp.commerceApp.models.category.CategoryMetadataField;
import com.commerceApp.commerceApp.dtos.categoryDtos.CategoryMetadataFieldDto;
import com.commerceApp.commerceApp.repositories.CategoryFieldRepository;

import com.commerceApp.commerceApp.util.EntityDtoMapping;
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

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryMetadataFieldService {
    @Autowired
    CategoryFieldRepository categoryFieldRepository;
    @Autowired
    ModelMapper modelMapper;

    CategoryMetadataField toCategoryMetadataField(CategoryMetadataFieldDto fieldDto){
        if(fieldDto == null)
            return null;
        return modelMapper.map(fieldDto, CategoryMetadataField.class);
    }
    public CategoryMetadataFieldDto toCategoryMetadataFieldDto(CategoryMetadataField field){
        if(field == null)
            return null;
        return modelMapper.map(field, CategoryMetadataFieldDto.class);
    }

    public ResponseEntity<BaseDto> addNewMetadataField(String fieldName) {
        CategoryMetadataField savedField = categoryFieldRepository.findByName(fieldName);
        BaseDto response;
        if(savedField!=null){
            response = new ErrorDto("Invalid Operation","Field already exists");
            return new ResponseEntity<BaseDto>(response, HttpStatus.CONFLICT);
        }

        savedField = new CategoryMetadataField();
        savedField.setName(fieldName);
        categoryFieldRepository.save(savedField);

        response = new ResponseDto<>(null, null);
        return new ResponseEntity<BaseDto>(response, HttpStatus.CREATED);
    }

    public ResponseEntity<List> getAllMetadataFields(String offset, String size, String sortByField, String order, Long categoryId) {

        Integer pageNo = Integer.parseInt(offset);
        Integer pageSize = Integer.parseInt(size);
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortByField).ascending());


        List<CategoryMetadataField> fields = categoryFieldRepository.findAll(pageable);
        List<CategoryMetadataFieldDto> responseData = new ArrayList<>();

        fields.forEach((field)->{
            CategoryMetadataFieldDto categoryMetadataFieldDto =toCategoryMetadataFieldDto(field);
            categoryMetadataFieldDto.setValues(null);
            responseData.add(categoryMetadataFieldDto);
        });
        return new ResponseEntity<>(responseData, HttpStatus.OK);

    }
}
