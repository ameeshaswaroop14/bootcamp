package com.SpringAssign.demoSpring;

import org.springframework.stereotype.Component;

@Component
public class JVM implements Competency{
    @Override
    public void getCompetency() {
        System.out.println("JVM");

    }
}
