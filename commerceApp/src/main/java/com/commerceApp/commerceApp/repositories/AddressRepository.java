package com.commerceApp.commerceApp.repositories;

import com.commerceApp.commerceApp.models.Address;
import com.commerceApp.commerceApp.models.Customer;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

@Repository
public interface AddressRepository extends CrudRepository<Address,Long> {
    //  @Modifying
    //@Transactional
    //@Query("delete from Address where id= :Id")

    //void deleteAddressById(@Param("Id") Long Id);
    // @Query("update from Address where id= :Id")
    //void updateAddressById(@Param("Id")Long Id);
    //Address findByEmail(String email);

}