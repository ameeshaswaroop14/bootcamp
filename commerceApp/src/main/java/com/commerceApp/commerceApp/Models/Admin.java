package com.commerceApp.commerceApp.Models;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "ADMIN")
@PrimaryKeyJoinColumn(name = "USER_ID")
public class Admin extends User{
    public Admin() {
    }

    public Admin(String email, String firstName, String middleName, String lastName) {
        super(email, firstName, middleName, lastName);
    }

}
