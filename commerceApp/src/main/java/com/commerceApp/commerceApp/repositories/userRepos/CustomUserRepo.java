package com.commerceApp.commerceApp.repositories.userRepos;

import com.commerceApp.commerceApp.models.User;
import com.commerceApp.commerceApp.models.product.ProductReview;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class CustomUserRepo {
    @PersistenceContext
    EntityManager entityManager;

    public CustomUserRepo(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    public List<User> findByEmail(String email){
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
        Root root = criteriaQuery.from(User.class);
        Predicate emailPredicate = criteriaBuilder.equal(root.get("email"), email);

        return entityManager.createQuery(criteriaQuery.where(emailPredicate)).getResultList();
    }
    public List<User> findByFirstName(String firstName){
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
        Root root = criteriaQuery.from(User.class);
        Predicate emailPredicate = criteriaBuilder.equal(root.get("firstName"), firstName);

        return entityManager.createQuery(criteriaQuery.where(emailPredicate)).getResultList();
    }
    public List<User> findByLastName(String lastName){
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
        Root root = criteriaQuery.from(User.class);
        Predicate emailPredicate = criteriaBuilder.equal(root.get("lastName"), lastName);

        return entityManager.createQuery(criteriaQuery.where(emailPredicate)).getResultList();
    }
    public List<User> findById(Long id){
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
        Root root = criteriaQuery.from(User.class);
        Predicate emailPredicate = criteriaBuilder.equal(root.get("id"), id);

        return entityManager.createQuery(criteriaQuery.where(emailPredicate)).getResultList();
    }


}
