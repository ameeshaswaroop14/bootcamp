package com.hibernate3.hibernate3.Repository;

import com.hibernate3.hibernate3.Model.AuthorUnidirectional;
import org.springframework.data.repository.CrudRepository;

public interface UnidirectionalRepository extends CrudRepository<AuthorUnidirectional, Integer> {
}
