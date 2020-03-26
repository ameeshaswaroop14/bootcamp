package com.hibernate3.hibernate3.Controller;

import com.hibernate3.hibernate3.Dao.ManytoManyDao;
import com.hibernate3.hibernate3.Repository.ManytoManyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.GeneratedValue;

@RestController
public class ManytoManyController {
    @Autowired
    ManytoManyDao manytoManyDao;

    @GetMapping("/mtom")
    void setdata(){
        manytoManyDao.setData();
    }
}
