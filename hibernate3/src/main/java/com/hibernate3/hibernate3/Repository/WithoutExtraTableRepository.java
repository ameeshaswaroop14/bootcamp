package com.hibernate3.hibernate3.Repository;

import com.hibernate3.hibernate3.Model.AuthorUnidirectional;
import com.hibernate3.hibernate3.Model.AuthorWithoutExtraTable;
import org.springframework.data.repository.CrudRepository;

public interface WithoutExtraTableRepository extends CrudRepository<AuthorWithoutExtraTable, Integer> {
}
