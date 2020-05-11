package com.commerceApp.commerceApp.repositories.productRepos;

import com.commerceApp.commerceApp.models.Customer;
import com.commerceApp.commerceApp.models.product.Product;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class CustomProductRepo {
    @PersistenceContext
    EntityManager entityManager;
    public List<Product> findAllBrandsByCategoryId(Long categoryId) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Product> query = builder.createQuery(Product.class);
        Root<Product> root = query.from(Product.class);
        Predicate predicate = builder.equal(root.get("categoryId"), categoryId);
        query.where(predicate);
        query.select(root);
        System.out.println("//////////////////////////////////" +entityManager.createQuery(query).getResultList());
        return entityManager.createQuery(query).getResultList();

    }
}
