package com.hibernate3.hibernate3.Model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "BookManytoMany")
public class BookManytoMany {
    @Id
    @GeneratedValue
    int id;
    String bookName;

    @ManyToMany(mappedBy = "bookManytoManySet")
    private Set<AuthorManytoMany> authorManytoManySet;

    public Set<AuthorManytoMany> getAuthorManytoManySet() {
        return authorManytoManySet;
    }

    public void setAuthorManytoManySet(Set<AuthorManytoMany> authorManytoManySet) {
        this.authorManytoManySet = authorManytoManySet;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }
}
