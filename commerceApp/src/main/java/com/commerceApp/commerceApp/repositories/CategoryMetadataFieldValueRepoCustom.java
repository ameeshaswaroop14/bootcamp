package com.commerceApp.commerceApp.repositories;

import com.commerceApp.commerceApp.models.category.CategoryMetadataField;
import com.commerceApp.commerceApp.models.category.CategoryMetadataFieldValues;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.*;
import java.util.List;

@Repository
public class CategoryMetadataFieldValueRepoCustom {
    @PersistenceContext
    EntityManager entityManager;

    public CategoryMetadataFieldValueRepoCustom(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<CategoryMetadataField> findAllFieldsOfCategoryById(Long categoryId) {
        CriteriaBuilder criteriaBuilder=entityManager.getCriteriaBuilder();
        CriteriaQuery<CategoryMetadataField> criteriaQuery=criteriaBuilder.createQuery(CategoryMetadataField.class);
        Root<CategoryMetadataField> root=criteriaQuery.from(CategoryMetadataField.class);
        Join<CategoryMetadataField,CategoryMetadataFieldValues> join=root.join("categoryMetadataFieldId", JoinType.INNER);
        Predicate predicate = criteriaBuilder.equal(root.get("categoryId"), categoryId);
        criteriaQuery.where(predicate);
        List<CategoryMetadataField>categoryMetadataFields=entityManager.createQuery(criteriaQuery).getResultList();
        System.out.println("///////////////////////"+categoryMetadataFields);
        return categoryMetadataFields;

    }

    List<Object> findAllValuesOfCategoryField(@Param("c_id") Long c_id, @Param("f_id") Long f_id) {
       return null;
    }
}


