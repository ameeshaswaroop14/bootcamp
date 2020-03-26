package com.hibernate3.hibernate3.Model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "AuthorWithoutExtraTable")
public class AuthorWithoutExtraTable {
    @Id
    @GeneratedValue
    int id;
    String name;

    @OneToMany(mappedBy = "authorWithoutExtraTable", cascade = CascadeType.ALL)
    private Set<BookWithoutExtraTable> bookWithoutExtraTables;

    public Set<BookWithoutExtraTable> getBookWithoutExtraTables() {
        return bookWithoutExtraTables;
    }

    public void setBookWithoutExtraTables(Set<BookWithoutExtraTable> bookWithoutExtraTables) {
        this.bookWithoutExtraTables = bookWithoutExtraTables;
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
