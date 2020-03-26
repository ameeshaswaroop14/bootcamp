package com.hibernate.hibernate2.Controller;

import com.hibernate.hibernate2.Repository.SingleInheritRepository;
import com.hibernate.hibernate2.Model.CompanySingle;
import com.hibernate.hibernate2.Model.CollegeSingle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class SingleInheritController {
    @Autowired
    SingleInheritRepository singleInheritRepository;

    @GetMapping("/say")
    String show(){
        return "hello me";
    }

    @GetMapping("/singleinherit")
    void setData(){
        CompanySingle companySingle =new CompanySingle();
        companySingle.setName("ameesha");
        companySingle.setAge(23);
        companySingle.setCompany("TTN");

        CollegeSingle collegeSingle =new CollegeSingle();
        collegeSingle.setName("swaroop");
        collegeSingle.setAge(24);
        collegeSingle.setCollege("AKTU");

        singleInheritRepository.save(companySingle);
        singleInheritRepository.save(collegeSingle);
    }


}
