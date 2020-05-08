package com.commerceApp.commerceApp.repositories;

import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;
/*
@Repository
public class CategoryMetadataFieldValueRepoCustom {
    @PersistenceContext
    EntityManager entityManager;

    public CategoryMetadataFieldValueRepoCustom(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    List<Object[]> findAllFieldsOfCategoryById( Long c_id) {
        CriteriaBuilder criteriaBuilder=entityManager.getCriteriaBuilder();
        CriteriaQuery criteriaQuery=criteriaBuilder.createQuery()

    }

    List<Object> findAllValuesOfCategoryField(@Param("c_id") Long c_id, @Param("f_id") Long f_id) {

    }
}


 */