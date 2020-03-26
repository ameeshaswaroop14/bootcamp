package com.hibernate3.hibernate3.Controller;

import com.hibernate3.hibernate3.Dao.UnidirectionalDao;
import com.hibernate3.hibernate3.Model.AuthorUnidirectional;
import com.hibernate3.hibernate3.Repository.UnidirectionalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UnidirectionalController {

    @Autowired
    UnidirectionalDao unidirectionalDao;
    @Autowired
    UnidirectionalRepository unidirectionalRepository;

    @GetMapping("/unidata")
    String setData(){
        AuthorUnidirectional authorUnidirectional=unidirectionalDao.setData();
        unidirectionalRepository.save(authorUnidirectional);
        return "unidirectional data set";
    }
}
