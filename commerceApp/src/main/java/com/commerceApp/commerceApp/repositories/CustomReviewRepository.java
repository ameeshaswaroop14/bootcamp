package com.commerceApp.commerceApp.repositories;

import com.commerceApp.commerceApp.models.Cart;
import com.commerceApp.commerceApp.models.product.ProductReview;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class CustomReviewRepository {
    @PersistenceContext
    EntityManager entityManager;

    public CustomReviewRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    public List<ProductReview>findByCustomerId(Long customer_user_id) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<ProductReview> criteriaQuery = criteriaBuilder.createQuery(ProductReview.class);
        Root root = criteriaQuery.from(ProductReview.class);
        Predicate idPredicate = criteriaBuilder.equal(root.get("author"), customer_user_id);

        return entityManager.createQuery(criteriaQuery.where(idPredicate)).getResultList();
    }
}
