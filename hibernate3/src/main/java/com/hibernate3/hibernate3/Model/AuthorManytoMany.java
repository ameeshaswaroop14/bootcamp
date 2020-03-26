package com.hibernate3.hibernate3.Model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "AuthorManytoMany")
public class AuthorManytoMany {
    @Id
    @GeneratedValue
    int id;
    String name;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "author_books",
            joinColumns = @JoinColumn(name = "author_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "book_id", referencedColumnName = "id"))
    private Set<BookManytoMany> bookManytoManySet;

    public Set<BookManytoMany> getBookManytoManySet() {
        return bookManytoManySet;
    }

    public void setBookManytoManySet(Set<BookManytoMany> bookManytoManySet) {
        this.bookManytoManySet = bookManytoManySet;
    }

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
}
