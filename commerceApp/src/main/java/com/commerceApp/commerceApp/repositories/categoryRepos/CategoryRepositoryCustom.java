package com.commerceApp.commerceApp.repositories.categoryRepos;

import com.commerceApp.commerceApp.models.category.Category;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.Root;

@Repository
public class CategoryRepositoryCustom {

    @PersistenceContext
    EntityManager entityManager;

    public CategoryRepositoryCustom(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    // @Transactional(rollbackFor = Exception.class)
    public void deleteCategoryById(Long id) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaDelete<Category> criteriaDelete = criteriaBuilder.createCriteriaDelete(Category.class);
        Root<Category> root = criteriaDelete.from(Category.class);
        criteriaDelete.where(criteriaBuilder.equal(root.get("id"), id));
        //entityManager.getTransaction().begin();
        //int rowsDeleted = entityManager.createQuery(criteriaDelete).executeUpdate();
        //System.out.println("entities deleted: " + rowsDeleted);
        //entityManager.getTransaction().commit();
        //entityManager.close();


    }
}
