package com.commerceApp.commerceApp.repositories;

import com.commerceApp.commerceApp.models.Cart;
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
public class CustomCartRepository {
    @PersistenceContext
    EntityManager entityManager;

    public CustomCartRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    public List<Cart > findCartByUserId(Long id) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Cart> criteriaQuery = criteriaBuilder.createQuery(Cart.class);
        Root root = criteriaQuery.from(Cart.class);
        Predicate idPredicate = criteriaBuilder.equal(root.get("user"), id);

        criteriaQuery.where(idPredicate);
        TypedQuery<Cart> query = entityManager.createQuery(criteriaQuery);
        System.out.println("////////////////" + query.getSingleResult());
        return query.getResultList();
    }

}


