package com.hibernate.hibernate2.Model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "CompanyJoined")
@PrimaryKeyJoinColumn(name = "id")
public class CompanyJoined extends EmployeeDouble {
    String company;

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }
}
