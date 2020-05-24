package com.commerceApp.commerceApp.repositories;

import com.commerceApp.commerceApp.models.Cart;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CartRepository extends CrudRepository<Cart,Long> {
    List<Cart>findAll();

    @Query(value = "select * from cart where customer_user_id = :c_u_id", nativeQuery = true)
    List<Cart> findCartByUserId(@Param("c_u_id") Long c_u_id);
}