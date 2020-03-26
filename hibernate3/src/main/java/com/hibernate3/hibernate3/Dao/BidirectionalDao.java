package com.hibernate3.hibernate3.Dao;

import com.hibernate3.hibernate3.Model.AuthorBidirectional;
import com.hibernate3.hibernate3.Model.AuthorUnidirectional;
import com.hibernate3.hibernate3.Model.BookBidirectional;
import com.hibernate3.hibernate3.Model.BookUnidirectional;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class BidirectionalDao {
    public AuthorBidirectional setData(){
        AuthorBidirectional authorBidirectional=new AuthorBidirectional();
        authorBidirectional.setName("swaroop");
        BookBidirectional bookBidirectional=new BookBidirectional();
        BookBidirectional bookBidirectional1=new BookBidirectional();
        bookBidirectional.setBookName("book12");
        bookBidirectional1.setBookName("book23");
        Set<BookBidirectional> bookBidirectionalSet=new HashSet<>();
        bookBidirectionalSet.add(bookBidirectional);
        bookBidirectionalSet.add(bookBidirectional1);
        authorBidirectional.setBookBidirectional(bookBidirectionalSet);
        return authorBidirectional;
    }
}
