package com.commerceApp.commerceApp.repositories.userRepos;

import com.commerceApp.commerceApp.models.Cart;
import com.commerceApp.commerceApp.models.User;
import com.commerceApp.commerceApp.models.product.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    User findByEmail(String email);

    User findByPassword(String password);

    List<User> findAll(Pageable pageable);



    List<User> findAll();

}
