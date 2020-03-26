package com.hibernate.hibernate2.Repository;

import com.hibernate.hibernate2.Model.EmployeePerClass;
import com.hibernate.hibernate2.Model.EmployeeSingle;
import org.springframework.data.repository.CrudRepository;

public interface PerClassRepository extends CrudRepository<EmployeePerClass, Integer> {

}
