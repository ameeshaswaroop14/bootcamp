package com.hibernate3.hibernate3.Model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "author_unidirectional")
public class AuthorUnidirectional {
    @Id
    @GeneratedValue
    int id;
    String name;

    @OneToMany(cascade = CascadeType.ALL)
    private Set<BookUnidirectional> bookUnidirectional;

    public Set<BookUnidirectional> getBookUnidirectional() {
        return bookUnidirectional;
    }

    public void setBookUnidirectional(Set<BookUnidirectional> bookUnidirectional) {
        this.bookUnidirectional = bookUnidirectional;
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
