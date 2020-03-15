package com.SpringAssign.demoSpring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Trainee {
    private String name;
    private int id;
    @Autowired
    private Competency competency;
    Trainee(Competency competency){
        this.competency=competency;
    }
    Trainee(){}


    public Trainee(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Trainee{" +
                "name='" + name + '\'' +
                ", id=" + id +
                '}';
    }



    public Competency getCompetency() {
        return competency;
    }
    public void setCompetency1(){
        JVM jvm=new JVM();
        jvm.getCompetency();

    }
}
