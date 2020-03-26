package com.hibernate3.hibernate3.Controller;

import com.hibernate3.hibernate3.Model.Caching;
import com.hibernate3.hibernate3.Repository.CachingRepository;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import java.util.Optional;

@RestController
public class CachingController {

    @Autowired
    CachingRepository cachingRepository;

    @Autowired
    EntityManager entityManager;

    @GetMapping("/session")
    void setData(){
        Caching caching=new Caching();
        caching.setName("ameesha");
        Caching caching1=new Caching();
        caching.setName("swaroop");
        cachingRepository.save(caching);
        cachingRepository.save(caching1);
        Session session= entityManager.unwrap(Session.class);
    }

    @GetMapping("/evict")
    void evicting(){
        Caching caching1 = null;
        Optional<Caching> caching = cachingRepository.findById(1);
        if (caching.isPresent())
            caching1 = caching.get();
        cachingRepository.findById(1);
        cachingRepository.findById(1);
        Session session = entityManager.unwrap(Session.class);
        session.evict(caching1);
        cachingRepository.findById(1);
    }


}
