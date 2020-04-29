package com.commerceApp.commerceApp.repositories;

import com.commerceApp.commerceApp.models.category.CategoryMetadataFieldValues;
import com.commerceApp.commerceApp.models.category.CategoryMetadataFieldValuesId;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryMetadataFieldValueRepo extends CrudRepository<CategoryMetadataFieldValues, CategoryMetadataFieldValuesId> {
    @Query(value = "select f.name from category_metadata_field f inner join " +
            "category_metadata_field_values v on " +
            "f.id=v.category_metadata_field_id " +
            "where v.category_id = :c_id", nativeQuery = true)
    List<Object[]> findAllFieldsOfCategoryById(@Param("c_id") Long c_id);


    @Query(value = "select v.value from category_metadata_field_values v" +
            " where v.category_metadata_field_id = :f_id and " +
            "v.category_id = :c_id", nativeQuery = true)
    List<Object> findAllValuesOfCategoryField(@Param("c_id") Long c_id, @Param("f_id") Long f_id);


    @Query(value = "select f.name, v.value from " +
            "category_metadata_field f " +
            "inner join " +
            "category_metadata_field_values v " +
            "on f.id = category_metadata_field_id " +
            "where v.category_id = :c_id", nativeQuery = true)
    List<Object[]> findAllFieldsAndValuesForLeafCategory(@Param("c_id") Long c_id);

}

