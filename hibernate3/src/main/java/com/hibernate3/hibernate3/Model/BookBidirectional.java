package com.hibernate3.hibernate3.Model;

import javax.persistence.*;

@Entity
@Table(name = "book_bidirectional")
public class BookBidirectional {
    @Id
    @GeneratedValue
    int id;
    String bookName;

    @ManyToOne
    @JoinColumn(name = "author_book")
    private AuthorBidirectional authorBidirectional;

    public AuthorBidirectional getAuthorBidirectional() {
        return authorBidirectional;
    }

    public void setAuthorBidirectional(AuthorBidirectional authorBidirectional) {
        this.authorBidirectional = authorBidirectional;
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
