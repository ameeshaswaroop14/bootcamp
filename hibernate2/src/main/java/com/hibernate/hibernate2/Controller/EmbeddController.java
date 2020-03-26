package com.hibernate.hibernate2.Controller;

import com.hibernate.hibernate2.Model.EmployeeEmbedded;
import com.hibernate.hibernate2.Model.Salary;
import com.hibernate.hibernate2.Repository.EmbeddRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmbeddController {
    @Autowired
    EmbeddRepository embeddRepository;

    @GetMapping("/yee")
    String  show(){
        return "hello there";
    }

    @GetMapping("/save")
    void embedd(){
        Salary salary=new Salary();
        salary.setBasicSalary(15000);
        salary.setBonusSalary(2000);
        salary.setSpecialAllowanceSalary(1890);
        salary.setTaxAmount(2000);
        EmployeeEmbedded employeeEmbedded=new EmployeeEmbedded();
        employeeEmbedded.setFirstName("Ameesha");
        employeeEmbedded.setLastName("Swaroop");
        employeeEmbedded.setAge(23);
        employeeEmbedded.setSalary(salary);
        embeddRepository.save(employeeEmbedded);

    }

}
