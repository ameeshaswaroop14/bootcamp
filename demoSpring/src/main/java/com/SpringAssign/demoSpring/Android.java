package com.SpringAssign.demoSpring;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class Android implements Competency {


    @Override
    public void getCompetency() {
        System.out.println("Android");

    }
}
