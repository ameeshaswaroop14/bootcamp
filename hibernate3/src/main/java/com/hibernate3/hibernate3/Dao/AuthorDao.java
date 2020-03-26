package com.hibernate3.hibernate3.Dao;

import com.hibernate3.hibernate3.Model.Author;
import com.hibernate3.hibernate3.Model.AuthorAddress;
import com.hibernate3.hibernate3.Model.Book;
import com.hibernate3.hibernate3.Model.Subject;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class AuthorDao {

    public Author authorData(){
        AuthorAddress authorAddress=new AuthorAddress();
        authorAddress.setStreetNumber(20);
        authorAddress.setLocation("Noida");
        authorAddress.setState("U.P.");
        Author author=new Author();
        author.setName("Ameesha");
        author.setAuthorAddress(authorAddress);
        Subject subject=new Subject();
        subject.setSubject("Spring boot");
        Subject subject1=new Subject();
        subject.setSubject("JVM");
        Subject subject2=new Subject();
        subject.setSubject("SQL");
        subject.setAuthor(author);
        subject1.setAuthor(author);
        subject2.setAuthor(author);
        author.addSubject(subject);
        author.addSubject(subject1);
        author.addSubject(subject2);
        Book book=new Book();
        book.setBookName("MyWriting");
        book.setAuthor(author);
        return author;
    }

}
