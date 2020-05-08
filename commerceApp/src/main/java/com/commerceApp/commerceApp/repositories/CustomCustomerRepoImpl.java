package com.commerceApp.commerceApp.repositories;

import com.commerceApp.commerceApp.models.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.List;

@Repository
public class CustomCustomerRepoImpl implements CustomCustomerRepo {
    @PersistenceContext
    private EntityManager entityManager;
    private static final Logger logger = LoggerFactory.getLogger(CustomCustomerRepoImpl.class);

    public CustomCustomerRepoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Customer findByEmail(String email) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Customer> query = builder.createQuery(Customer.class);
        Root<Customer> root = query.from(Customer.class);
        Predicate predicate = builder.equal(root.get("email"), email);
         query.where(predicate);

        TypedQuery<Customer> query1 = entityManager.createQuery(query);
        //
        System.out.println("//////////////////////////////////" + query1.getSingleResult());
        logger.info(String.valueOf(query.where(predicate)));

        return query1.getSingleResult();

    }


    @Override
    public List<Customer> findAll() {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Customer> query = builder.createQuery(Customer.class);
        Root<Customer> root = query.from(Customer.class);
        query.select(root);
        System.out.println("//////////////////////////////////" +entityManager.createQuery(query).getResultList());
        return entityManager.createQuery(query).getResultList();

    }

    @Override
    public List<Customer> findAll(Pageable pageable) {
        return null;
    }
}

