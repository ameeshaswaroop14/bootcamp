package com.hibernate.hibernate2.Repository;

import com.hibernate.hibernate2.Model.EmployeeEmbedded;
import org.springframework.data.repository.CrudRepository;

public interface EmbeddRepository extends CrudRepository<EmployeeEmbedded, Integer> {
}
