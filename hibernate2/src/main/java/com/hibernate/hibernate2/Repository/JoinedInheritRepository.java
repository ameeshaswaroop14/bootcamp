package com.hibernate.hibernate2.Repository;

import com.hibernate.hibernate2.Model.EmployeeDouble;
import org.springframework.data.repository.CrudRepository;

public interface JoinedInheritRepository extends CrudRepository<EmployeeDouble, Integer> {

}
