package com.commerceApp.commerceApp.events;


import com.commerceApp.commerceApp.models.AuditRevisionEntity;
import com.commerceApp.commerceApp.services.CurrentUserService;

import org.hibernate.envers.RevisionListener;
import org.springframework.beans.factory.annotation.Autowired;
public class AuditRevisionListener implements RevisionListener {
    @Autowired
    CurrentUserService currentUserService;

    @Override
    public void newRevision(Object revisionEntity) {
        AuditRevisionEntity audit = (AuditRevisionEntity) revisionEntity;

        audit.setUsername(currentUserService.getUser());
    }
}

