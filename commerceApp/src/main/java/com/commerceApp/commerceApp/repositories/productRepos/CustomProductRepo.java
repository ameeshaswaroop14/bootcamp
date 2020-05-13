package com.commerceApp.commerceApp.repositories.productRepos;

import com.commerceApp.commerceApp.models.product.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.*;
import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;

@Repository
public class CustomProductRepo {
    @PersistenceContext
    EntityManager entityManager;
    public Collection<? extends String> findAllBrandsByCategoryId(Long categoryId) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Product> query = builder.createQuery(Product.class);
        Root<Product> root = query.from(Product.class);
        Predicate predicate = builder.equal(root.get("category_id"), categoryId);
        query.where(predicate);
        return (Collection<? extends String>) query.select(root.get("brand"));
      //  System.out.println("//////////////////////////////////" +entityManager.createQuery(query).getResultList());
       // return entityManager.createQuery(query).getResultList();

    }
    @Transactional
    public void deleteByProductId(Long id){
        CriteriaBuilder criteriaBuilder=entityManager.getCriteriaBuilder();
        CriteriaDelete<Product> criteriaDelete = criteriaBuilder.createCriteriaDelete(Product.class);
        Root<Product> root = criteriaDelete.from(Product.class);
        Predicate predicate = criteriaBuilder.equal(root.get("id"), id);
        entityManager.createQuery(criteriaDelete.where(predicate)).executeUpdate();

    }/*
    private RedisTemplate<String,Product> redisTemplate;
    private HashOperations hashOperations;

    public CustomProductRepo(RedisTemplate<String, Product> redisTemplate, HashOperations hashOperations) {
        this.redisTemplate = redisTemplate;
        hashOperations=redisTemplate.opsForHash();
    }

    public List<Product> getAllProducts(Pageable pageable){
        return hashOperations.entries("Product");
    }

     */

}
