package com.database2.demoDatabase;

import javax.persistence.*;

@Entity
public class Employee {
    @Id

    private Integer employeeid;
    private String firstname;
    private String lastname;
    private String location;
    private int age;

    public Integer getEmployeeid() {
        return employeeid;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setEmployeeid(int employeeid) {
        this.employeeid = employeeid;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}



