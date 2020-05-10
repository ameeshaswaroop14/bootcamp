package com.commerceApp.commerceApp.repositories.productRepos;

import com.commerceApp.commerceApp.models.product.ProductVariation;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface ProductVariationRepository extends CrudRepository<ProductVariation,Long> {
    List<ProductVariation> findAll();
    List<ProductVariation> findAll(Pageable pageable);

    List<ProductVariation> findByProductId(Long id);
    List<ProductVariation> findByProductId(Long id, Pageable pageable);

    @Modifying
    @Transactional
    @Query(value = "delete from product_variation where product_id = :p_id", nativeQuery = true)
    void deleteByProductId(@Param("p_id") Long p_id);
}
