package com.commerceApp.commerceApp.repositories.categoryRepos;

import com.commerceApp.commerceApp.models.category.CategoryMetadataField;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryFieldRepository extends CrudRepository<CategoryMetadataField, Long> {
    CategoryMetadataField findByName(String name);
    List<CategoryMetadataField> findAll(Pageable pageable);
}
