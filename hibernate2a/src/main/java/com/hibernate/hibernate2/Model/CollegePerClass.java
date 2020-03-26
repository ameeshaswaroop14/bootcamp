package com.hibernate.hibernate2.Model;


import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
public class CollegePerClass extends EmployeePerClass {
    String college;

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }
}
