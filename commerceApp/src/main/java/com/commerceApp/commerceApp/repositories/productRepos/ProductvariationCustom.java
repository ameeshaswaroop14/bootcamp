package com.commerceApp.commerceApp.repositories.productRepos;

import com.commerceApp.commerceApp.models.category.Category;
import com.commerceApp.commerceApp.models.product.ProductVariation;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

@Repository
public class ProductvariationCustom {
    @PersistenceContext
    EntityManager entityManager;

    @Transactional
   public void deleteByProductId(Long id){
       CriteriaBuilder criteriaBuilder=entityManager.getCriteriaBuilder();
       CriteriaDelete<ProductVariation> criteriaDelete = criteriaBuilder.createCriteriaDelete(ProductVariation.class);
       Root<ProductVariation> root = criteriaDelete.from(ProductVariation.class);
       Predicate predicate = criteriaBuilder.equal(root.get("id"), id);
       entityManager.createQuery(criteriaDelete.where(predicate)).executeUpdate();

   }
}
