package com.commerceApp.commerceApp.repositories;

import com.commerceApp.commerceApp.models.Cart;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public interface CartRepository extends CrudRepository<Cart,Long> {


}
