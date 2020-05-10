package com.commerceApp.commerceApp.repositories.productRepos;

import com.commerceApp.commerceApp.models.product.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

public interface ProductRepository extends CrudRepository<Product, Long> {
    Product findByName(String name);

    List<Product> findAll(Pageable pageable);

    List<Product> findByBrandAndCategoryId(String brand, Long id, Pageable pageable);

    List<Product> findByBrand(String brand, Pageable pageable);

    Set<Product> findByBrand(Long categoryId);

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
    @Query(value = "select brand from product where category_id = :c_id", nativeQuery = true)
    List<String> findAllBrandsByCategoryId(@Param("c_id") Long c_id);
}
