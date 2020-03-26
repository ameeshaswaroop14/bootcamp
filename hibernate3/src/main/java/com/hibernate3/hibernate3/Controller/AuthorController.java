package com.hibernate3.hibernate3.Controller;

import com.hibernate3.hibernate3.Dao.AuthorDao;
import com.hibernate3.hibernate3.Model.Author;
import com.hibernate3.hibernate3.Repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthorController {

    @Autowired
    AuthorRepository authorRepository;

    @Autowired
    AuthorDao authorDao;

    @GetMapping("/hey")
    String say(){
        return "say hey";
    }

    @GetMapping("/saveauthor")
    String saveAuthor(){
        Author author=authorDao.authorData();
        authorRepository.save(author);
        return "Record added for author successfully";
    }
}
