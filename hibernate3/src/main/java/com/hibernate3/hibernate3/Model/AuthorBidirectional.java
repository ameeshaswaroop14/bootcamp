package com.hibernate3.hibernate3.Model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "AuthorBidirectional")
public class AuthorBidirectional {
    @Id
    @GeneratedValue
    int id;
    String name;

    @OneToMany(cascade = CascadeType.ALL)
    private Set<BookBidirectional> bookBidirectional;

    public Set<BookBidirectional> getBookUnidirectional() {
        return bookBidirectional;
    }

    public void setBookBidirectional(Set<BookBidirectional> bookBidirectional) {
        this.bookBidirectional = bookBidirectional;
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
