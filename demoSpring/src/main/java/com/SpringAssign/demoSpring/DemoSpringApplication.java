package com.SpringAssign.demoSpring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class DemoSpringApplication {

	public static void main(String[] args)  {
		SpringApplication.run(DemoSpringApplication.class, args);
		ApplicationContext applicationContext=SpringApplication.run(DemoSpringApplication.class,args);
		/*Trainee emp=applicationContext.getBean(Trainee.class);
		emp.setId(1);
		emp.setName("Ameesha");
		emp.toString();*/

	}

}
