package com.commerceApp.commerceApp.models;

import org.hibernate.envers.Audited;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import java.io.Serializable;
@Audited
@Entity
@Table(name = "ADMIN")
@PrimaryKeyJoinColumn(name = "USER_ID")
public class Admin extends User implements Serializable {
    public Admin() {
    }

    public Admin(String email, String firstName, String middleName, String lastName) {
        super(email, firstName, middleName, lastName);
    }

}
