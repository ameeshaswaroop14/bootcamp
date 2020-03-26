package com.hibernate.hibernate2.Model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
public class CompanyPerClass extends EmployeePerClass {
    String company;

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }
}
