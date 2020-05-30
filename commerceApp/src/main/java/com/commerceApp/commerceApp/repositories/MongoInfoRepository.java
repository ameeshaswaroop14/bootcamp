package com.commerceApp.commerceApp.repositories;

import com.commerceApp.commerceApp.models.MongoInfo;
import com.commerceApp.commerceApp.models.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Date;
import java.util.List;

@Repository
public class MongoInfoRepository {
    @PersistenceContext
    EntityManager entityManager;

    public MongoInfoRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    public List<MongoInfo> findByUsername(String username){
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<MongoInfo> criteriaQuery = criteriaBuilder.createQuery(MongoInfo.class);
        Root root = criteriaQuery.from(MongoInfo.class);
        Predicate emailPredicate = criteriaBuilder.equal(root.get("username"), username);

        return entityManager.createQuery(criteriaQuery.where(emailPredicate)).getResultList();
    }
    public List<MongoInfo> findByDate(String date){
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<MongoInfo> criteriaQuery = criteriaBuilder.createQuery(MongoInfo.class);
        Root root = criteriaQuery.from(MongoInfo.class);
        Predicate emailPredicate = criteriaBuilder.equal(root.get("date"), date);

        return entityManager.createQuery(criteriaQuery.where(emailPredicate)).getResultList();
    }
    public List<MongoInfo> findById(String id){
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<MongoInfo> criteriaQuery = criteriaBuilder.createQuery(MongoInfo.class);
        Root root = criteriaQuery.from(MongoInfo.class);
        Predicate emailPredicate = criteriaBuilder.equal(root.get("id"), id);

        return entityManager.createQuery(criteriaQuery.where(emailPredicate)).getResultList();
    }
}
