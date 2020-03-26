package com.hibernate3.hibernate3.Dao;

import com.hibernate3.hibernate3.Model.AuthorManytoMany;
import com.hibernate3.hibernate3.Model.BookManytoMany;
import com.hibernate3.hibernate3.Repository.ManytoManyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;

@Component
public class ManytoManyDao {
    @Autowired
    ManytoManyRepository authorManytoManyRepository;
    public void setData(){
        AuthorManytoMany authorManytoMany = new AuthorManytoMany();
        authorManytoMany.setName("ameesha");
        BookManytoMany bookManytoMany = new BookManytoMany();
        bookManytoMany.setBookName("Book0000");
        BookManytoMany bookManytoMany1 = new BookManytoMany();
        bookManytoMany1.setBookName("Book9900");
        HashSet<BookManytoMany> bookManytoManyHashSet = new HashSet<>();
        bookManytoManyHashSet.add(bookManytoMany);
        bookManytoManyHashSet.add(bookManytoMany1);
        authorManytoMany.setBookManytoManySet(bookManytoManyHashSet);
        AuthorManytoMany authorManytoMany1 = new AuthorManytoMany();
        authorManytoMany1.setName("Chandler");
        HashSet<AuthorManytoMany> authorManytoManyHashSet = new HashSet<>();
        authorManytoManyHashSet.add(authorManytoMany);
        authorManytoManyHashSet.add(authorManytoMany1);
        bookManytoMany.setAuthorManytoManySet(authorManytoManyHashSet);
        authorManytoManyRepository.save(authorManytoMany);
        authorManytoManyRepository.save(authorManytoMany1);
    }
}
