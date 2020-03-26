package com.hibernate.hibernate2.Repository;

import com.hibernate.hibernate2.Model.EmployeesModel;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface EmployeesRepository extends PagingAndSortingRepository<EmployeesModel, Integer> {

    @Query("select firstName, lastName from EmployeesModel where salary >(select AVG(salary) from EmployeesModel)")
    List<Object[]> partialSelect(Pageable pageable);

    @Query("select AVG(salary) from EmployeesModel")
    int avgsal();

    @Transactional
    @Query("update EmployeesModel set salary=:nsal where salary<:avg")
    @Modifying
    void updateSalary(@Param("nsal") int nsal, @Param("avg") int avg);

    @Query("select MIN(salary) from EmployeesModel")
    int minsal();

    @Transactional
    @Query("delete from EmployeesModel where salary=:msal")
    @Modifying
    void deleteSalary(@Param("msal") int msal);

    @Query(value = "select emp_id, emp_first_name, emp_age from employee_table where emp_last_name like '%singh'", nativeQuery = true)
    List<Object[]> findSingh();

    @Transactional
    @Query(value = "delete from employee_table where emp_age>:lage", nativeQuery = true)
    @Modifying
    void deleteOld(@Param("lage") int lage);


}
