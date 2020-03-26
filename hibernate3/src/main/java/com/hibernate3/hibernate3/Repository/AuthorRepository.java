package com.hibernate3.hibernate3.Repository;

import com.hibernate3.hibernate3.Model.Author;
import org.springframework.data.repository.CrudRepository;

public interface AuthorRepository extends CrudRepository<Author, Integer> {
}
