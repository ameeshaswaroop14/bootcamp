package com.commerceApp.commerceApp.repositories;

import com.commerceApp.commerceApp.models.AuditRevisionEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuditRevisionEntityRepo extends CrudRepository<AuditRevisionEntity,String> {
}
