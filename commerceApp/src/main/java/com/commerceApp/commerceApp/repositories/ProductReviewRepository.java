package com.commerceApp.commerceApp.repositories;

import com.commerceApp.commerceApp.models.product.ProductReview;
import org.springframework.data.repository.CrudRepository;

public interface ProductReviewRepository extends CrudRepository<ProductReview,Long> {
}
