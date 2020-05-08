package com.commerceApp.commerceApp.repositories;

import com.commerceApp.commerceApp.models.category.Category;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
/*
public class CategoryRepositoryImpl implements CategoryRepoCustom {
    @PersistenceContext
    private EntityManager entityManager;


    public CategoryRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Category> findAll() {
        CriteriaBuilder criteriaBuilder=entityManager.getCriteriaBuilder();
        CriteriaQuery<Category> criteriaQuery = criteriaBuilder.createQuery(Category.class);
        Root<Category> categoryRoot = criteriaQuery.from(Category.class);
        List<Root<Category>> predicates = new ArrayList<>();
        predicates.add(categoryRoot);
        criteriaQuery.where(predicates.toArray(new Predicate[0]));

        TypedQuery<Category> query = entityManager.createQuery(criteriaQuery);
        return query.getResultList();


    }

    @Override
    public List<Category> findAll(Pageable pageable) {
        return null;
    }
}

 */




