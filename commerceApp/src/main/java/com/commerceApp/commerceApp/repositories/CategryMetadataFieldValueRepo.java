package com.commerceApp.commerceApp.repositories;

import com.commerceApp.commerceApp.models.category.CategoryMetadataFieldValues;
import com.commerceApp.commerceApp.models.category.CategoryMetadataFieldValuesId;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategryMetadataFieldValueRepo extends CrudRepository<CategoryMetadataFieldValues, CategoryMetadataFieldValuesId> {
}
