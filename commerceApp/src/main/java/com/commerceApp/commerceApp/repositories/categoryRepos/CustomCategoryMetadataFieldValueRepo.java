package com.commerceApp.commerceApp.repositories.categoryRepos;

import com.commerceApp.commerceApp.models.category.CategoryMetadataField;
import com.commerceApp.commerceApp.models.category.CategoryMetadataFieldValues;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.*;
import java.util.Collection;

@Repository
public class CustomCategoryMetadataFieldValueRepo {
    @PersistenceContext
    EntityManager entityManager;

    public CustomCategoryMetadataFieldValueRepo(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public Collection<CategoryMetadataField> findAllFieldsOfCategoryById(Long id) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<CategoryMetadataField> cq = criteriaBuilder.createQuery(CategoryMetadataField.class);

        Root<CategoryMetadataField> categoryMetadataFieldRoot = cq.from(CategoryMetadataField.class);
        Join<CategoryMetadataField, CategoryMetadataFieldValues> join = categoryMetadataFieldRoot.join("categoryMetadataFieldId");
        Predicate predicate = criteriaBuilder.equal(categoryMetadataFieldRoot.get("id"), id);
        cq.where(predicate);
        //TypedQuery<CategoryMetadataField> query1 = entityManager.createQuery(cq);
        //
        // System.out.println("//////////////////////////////////" + query1.getSingleResult());
        return entityManager.createQuery(cq).getResultList();

        // return query1.getSingleResult();

    }
}
   /* public List <Object[]>findAllValuesOfCategoryField()
    CriteriaBuilder builder = entityManager.getCriteriaBuilder();
    CriteriaQuery<CategoryMetadataFieldValues> query = builder.createQuery(CategoryMetadataFieldValues.class);
    Root<Customer> root = query.from(Customer.class);
    Predicate predicate = builder.equal(root.get("categoryMetadataFieldId"), email);
         query.where(predicate);

    TypedQuery<Customer> query1 = entityManager.createQuery(query);
    //
        System.out.println("//////////////////////////////////" + query1.getSingleResult());
        logger.info(String.valueOf(query.where(predicate)));

        return query1.getSingleResult();
}

    */
