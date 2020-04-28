package com.commerceApp.commerceApp.repositories;

import com.commerceApp.commerceApp.models.product.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.List;

public interface ProductRepository extends CrudRepository<Product,Long> {
    Product findByName(String name);
    List<Product> findAll(Pageable pageable);
    List<Product> findByBrandAndCategoryId(String brand, Long id, Pageable pageable);
    List<Product> findByBrand(String brand, Pageable pageable);
    List<Product> findByCategoryId(Long id, Pageable pageable);
   /* @Modifying
    @Transactional
    @Query(value = "delete from product where id = :id", nativeQuery = true)
    void deleteProductById(@PathVariable("id") Long id);


   @Modifying
   @Query(value = "delete from product  where id = id",
           nativeQuery = true)
   void deleteProductById(@Param("id")Long id);


    */
    @Override
    @Transactional
    void deleteById(Long id);
}
