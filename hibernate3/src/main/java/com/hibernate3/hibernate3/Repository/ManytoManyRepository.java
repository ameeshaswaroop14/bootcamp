package com.hibernate3.hibernate3.Repository;

import com.hibernate3.hibernate3.Model.AuthorManytoMany;
import org.springframework.data.repository.CrudRepository;

public interface ManytoManyRepository extends CrudRepository<AuthorManytoMany, Integer> {
}
