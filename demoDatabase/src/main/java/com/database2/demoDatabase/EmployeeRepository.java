package com.database2.demoDatabase;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface EmployeeRepository extends PagingAndSortingRepository<Employee, Integer> {
  List<Employee> findByFirstname(String name);
    List<Employee> findByLastnameLike(String name);
    List<Employee> findByAgeBetween(int age1,int age2);
}


