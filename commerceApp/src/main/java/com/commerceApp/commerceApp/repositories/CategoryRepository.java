package com.commerceApp.commerceApp.repositories;

import com.commerceApp.commerceApp.Models.Category;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CategoryRepository extends CrudRepository<Category,Long> {
    Category findByName(String categoryname);

    @Query(value = "select * from category where parent_id is null", nativeQuery = true)
    List<Category> findByParentIdIsNull();

    List<Category> findAll();
    List<Category> findAll(Pageable pageable);

    @Query(value = "delete from category_metadata_field_values where category_id = :Id", nativeQuery = true)
    void deleteCategoryById(@Param("Id") Long Id);
}
