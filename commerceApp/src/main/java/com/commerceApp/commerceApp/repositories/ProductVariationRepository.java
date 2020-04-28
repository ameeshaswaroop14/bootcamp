package com.commerceApp.commerceApp.repositories;

import com.commerceApp.commerceApp.models.product.ProductVariation;
import org.springframework.data.repository.CrudRepository;

public interface ProductVariationRepository extends CrudRepository<ProductVariation,Long> {
}
