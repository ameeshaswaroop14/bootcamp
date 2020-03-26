package com.hibernate3.hibernate3.Controller;

import com.hibernate3.hibernate3.Dao.BidirectionalDao;
import com.hibernate3.hibernate3.Dao.UnidirectionalDao;
import com.hibernate3.hibernate3.Model.AuthorBidirectional;
import com.hibernate3.hibernate3.Model.AuthorUnidirectional;
import com.hibernate3.hibernate3.Repository.BidirectionalRepository;
import com.hibernate3.hibernate3.Repository.UnidirectionalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BidirectionalController {
    @Autowired
    BidirectionalDao bidirectionalDao;
    @Autowired
    BidirectionalRepository bidirectionalRepository;

    @GetMapping("/bidata")
    String setData(){
        AuthorBidirectional authorBidirectional=bidirectionalDao.setData();
        bidirectionalRepository.save(authorBidirectional);
        return "bidirectional data set";
    }

}
