package com.springRestful2.SpringRestful2.ModelClasses;

import org.springframework.stereotype.Component;


public class EmployeesV2 {
    int id;
    String  name;
    String email;
    String password;

    public EmployeesV2(int id, String name, String email, String password) {
        this.id = id;
        this.name = name;
        this.email= email;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail(){ return email;}

    public void setEmail(String email){this.email=email;}

    @Override
    public String toString() {
        return "EmployeesV1{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
