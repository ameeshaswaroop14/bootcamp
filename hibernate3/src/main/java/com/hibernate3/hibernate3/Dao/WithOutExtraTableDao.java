package com.hibernate3.hibernate3.Dao;

import com.hibernate3.hibernate3.Model.AuthorBidirectional;
import com.hibernate3.hibernate3.Model.AuthorWithoutExtraTable;
import com.hibernate3.hibernate3.Model.BookBidirectional;
import com.hibernate3.hibernate3.Model.BookWithoutExtraTable;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class WithOutExtraTableDao {
    public AuthorWithoutExtraTable setData(){
        AuthorWithoutExtraTable authorWithoutExtraTable=new AuthorWithoutExtraTable();
        authorWithoutExtraTable.setName("swaroop");
        BookWithoutExtraTable bookWithoutExtraTable=new BookWithoutExtraTable();
        BookWithoutExtraTable bookWithoutExtraTable1=new BookWithoutExtraTable();
        bookWithoutExtraTable.setBookName("book4342");
        bookWithoutExtraTable1.setBookName("book646");
        Set<BookWithoutExtraTable> bookWithoutExtraTableSet=new HashSet<>();
        bookWithoutExtraTableSet.add(bookWithoutExtraTable);
        bookWithoutExtraTableSet.add(bookWithoutExtraTable1);
        authorWithoutExtraTable.setBookWithoutExtraTables(bookWithoutExtraTableSet);
        return authorWithoutExtraTable;
    }
}
