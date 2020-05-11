package com.commerceApp.commerceApp.repositories;

import com.commerceApp.commerceApp.models.Address;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import javax.transaction.Transactional;

@Repository
public class AddressRepositoryCustom {
    @PersistenceContext
    private EntityManager entityManager;

    public AddressRepositoryCustom(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public Address findAdressById(Long id){
        CriteriaBuilder criteriaBuilder=entityManager.getCriteriaBuilder();
        CriteriaQuery<Address> criteriaQuery=criteriaBuilder.createQuery(Address.class);
        Root root=criteriaQuery.from(Address.class);
        Predicate idPredicate=criteriaBuilder.equal(root.get("id"),id);
        criteriaQuery.where(idPredicate);
        TypedQuery<Address> query=entityManager.createQuery(criteriaQuery);
        System.out.println("////////////////"+query.getSingleResult());
        return query.getSingleResult();
    }
    @Transactional
    public void deleteAddressById(Long id){
        CriteriaBuilder criteriaBuilder=entityManager.getCriteriaBuilder();
        CriteriaDelete<Address> criteriaQuery=criteriaBuilder.createCriteriaDelete(Address.class);
        Root root=criteriaQuery.from(Address.class);
        Predicate predicateId = criteriaBuilder.equal(root.get("id"), id);
         entityManager.createQuery(  criteriaQuery.where(predicateId)).executeUpdate();
       // TypedQuery<Address> query1 = (TypedQuery<Address>) entityManager.createQuery(criteriaQuery).executeUpdate();
        //System.out.println("//////////////////////////////////" + query1.getSingleResult());
        //return query1.getSingleResult();


    }
}

