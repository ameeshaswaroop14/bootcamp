package com.hibernate3.hibernate3.Controller;

import com.hibernate3.hibernate3.Dao.BidirectionalDao;
import com.hibernate3.hibernate3.Dao.WithOutExtraTableDao;
import com.hibernate3.hibernate3.Model.AuthorBidirectional;
import com.hibernate3.hibernate3.Model.AuthorWithoutExtraTable;
import com.hibernate3.hibernate3.Repository.BidirectionalRepository;
import com.hibernate3.hibernate3.Repository.WithoutExtraTableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WithoutExtraTableController {
    @Autowired
    WithOutExtraTableDao withOutExtraTableDao;
    @Autowired
    WithoutExtraTableRepository withoutExtraTableRepository;

    @GetMapping("/notabledata")
    String setData(){
        AuthorWithoutExtraTable authorWithoutExtraTable=withOutExtraTableDao.setData();
        withoutExtraTableRepository.save(authorWithoutExtraTable);
        return "data set without creating extra data";
    }

}
