package com.hibernate.hibernate2.Controller;

import com.hibernate.hibernate2.Model.CollegePerClass;
import com.hibernate.hibernate2.Model.CollegeSingle;
import com.hibernate.hibernate2.Model.CompanyPerClass;
import com.hibernate.hibernate2.Model.CompanySingle;
import com.hibernate.hibernate2.Repository.PerClassRepository;
import com.hibernate.hibernate2.Repository.SingleInheritRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class PerClassController {
    @Autowired
    PerClassRepository perClassRepository;

    @GetMapping("/sayme")
    String show(){
        return "hello me";
    }

    @GetMapping("/perclass")
    void setData(){
        CompanyPerClass companyPerClass =new CompanyPerClass();
        companyPerClass.setName("ameesha");
        companyPerClass.setAge(23);
        companyPerClass.setCompany("TTN");

        CollegePerClass collegePerClass =new CollegePerClass();
        collegePerClass.setName("swaroop");
        collegePerClass.setAge(24);
        collegePerClass.setCollege("AKTU");

        perClassRepository.save(companyPerClass);
        perClassRepository.save(collegePerClass);
    }


}
