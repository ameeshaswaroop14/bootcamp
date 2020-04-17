package com.commerceApp.commerceApp.repositories;

import com.commerceApp.commerceApp.Models.Address;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface AddressRepository extends CrudRepository<Address,Long> {
    @Modifying
    @Transactional
   @Query("delete from Address where id= :Id")

    void deleteAddressById(@Param("Id") Long Id);
   // @Query("update from Address where id= :Id")
    //void updateAddressById(@Param("Id")Long Id);
    //Address findByEmail(String email);

}
