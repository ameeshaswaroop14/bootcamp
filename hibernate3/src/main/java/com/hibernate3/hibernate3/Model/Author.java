package com.hibernate3.hibernate3.Model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "author")
public class Author {
    @Id
    @GeneratedValue
    int id;
    String name;

    @Embedded
    AuthorAddress authorAddress;

    @OneToMany(mappedBy = "author",cascade = CascadeType.ALL)
    private Set<Subject> subjects;

    @OneToOne(mappedBy = "author")
    private Book book;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AuthorAddress getAuthorAddress() {
        return authorAddress;
    }

    public void setAuthorAddress(AuthorAddress authorAddress) {
        this.authorAddress = authorAddress;
    }

    public Set<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(Set<Subject> subjects) {
        this.subjects = subjects;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public void addSubject(Subject subject){
        if(subject != null){
            if(subjects==null)
                subjects=new HashSet<>();
            subject.setAuthor(this);
            subjects.add(subject);
        }
    }
}
