package com.commerceApp.commerceApp.repositories.productRepos;

import com.commerceApp.commerceApp.models.product.Product;
import com.commerceApp.commerceApp.models.product.ProductReview;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductReviewRepository extends CrudRepository<ProductReview, Long> {
    List<ProductReview> findByProductId(Long productId);

  // @Query(value = "select review from product_review where customer_user_id= : p_id", nativeQuery = true)
    //List<ProductReview> findByCustomerId(@Param("p_id") Long p_id);

}
