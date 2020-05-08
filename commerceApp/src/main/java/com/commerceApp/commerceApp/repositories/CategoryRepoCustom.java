package com.commerceApp.commerceApp.repositories;

import com.commerceApp.commerceApp.models.category.Category;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CategoryRepoCustom {

    List<Category> findAll();
    List<Category> findAll(Pageable pageable);
}
