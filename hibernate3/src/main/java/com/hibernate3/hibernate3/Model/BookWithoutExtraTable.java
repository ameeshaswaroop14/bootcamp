package com.hibernate3.hibernate3.Model;

import javax.persistence.*;

@Entity
@Table(name = "BookWithoutExtraTable")
public class BookWithoutExtraTable {
    @Id
    @GeneratedValue
    int id;
    String bookName;

    @ManyToOne
    @JoinColumn(name = "author_books")
    private AuthorWithoutExtraTable authorWithoutExtraTable;

    public AuthorWithoutExtraTable getAuthorWithoutExtraTable() {
        return authorWithoutExtraTable;
    }

    public void setAuthorWithoutExtraTable(AuthorWithoutExtraTable authorWithoutExtraTable) {
        this.authorWithoutExtraTable = authorWithoutExtraTable;
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
