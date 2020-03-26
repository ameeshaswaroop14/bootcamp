package com.hibernate.hibernate2.Repository;

import org.springframework.data.repository.CrudRepository;
import com.hibernate.hibernate2.Model.EmployeeSingle;

public interface SingleInheritRepository extends CrudRepository<EmployeeSingle, Integer> {

}
