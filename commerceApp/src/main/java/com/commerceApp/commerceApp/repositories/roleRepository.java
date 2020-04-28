package com.commerceApp.commerceApp.repositories;

import com.commerceApp.commerceApp.models.Role;
import org.springframework.data.repository.CrudRepository;

public interface roleRepository extends CrudRepository<Role,Integer> {
    Role findByAuthority(String Authority);
}
