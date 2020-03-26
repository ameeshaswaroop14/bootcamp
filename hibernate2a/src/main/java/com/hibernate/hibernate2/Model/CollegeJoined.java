package com.hibernate.hibernate2.Model;


import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "CollegeJoined")
@PrimaryKeyJoinColumn(name = "id")
public class CollegeJoined extends EmployeeDouble {
    String college;

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }
}
