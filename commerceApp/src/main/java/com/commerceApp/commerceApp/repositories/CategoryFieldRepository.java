package com.commerceApp.commerceApp.repositories;

import com.commerceApp.commerceApp.Models.CategoryMetadataField;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryFieldRepository extends CrudRepository<CategoryMetadataField, Long> {
    CategoryMetadataField findByName(String name);
}
