package com.springRestful2.SpringRestful2.ModelClasses;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import org.springframework.stereotype.Component;

@ApiModel(description="Model of a user.")
@JsonFilter("ignoring")
public class UsersModel {
    private int id;
    private String name;
    private int age;
   @JsonIgnore
    private String password;

    public UsersModel(int id, String name, int age, String password) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.password=password;
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    @Override
    public String toString() {
        return "UsersModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
