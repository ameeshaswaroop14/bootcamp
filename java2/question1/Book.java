package java2.question1;

import java.util.ArrayList;
import java.util.List;

public abstract class Book {
    BaseEntity be;
    int isbn;
    List<Author> a =new ArrayList<>();

    public BaseEntity getBe() {
        return be;
    }

    public void setBe(BaseEntity be) {
        this.be = be;
    }

    public int getIsbn() {
        return isbn;
    }

    public void setIsbn(int isbn) {
        this.isbn = isbn;
    }

    public List<Author> getA() {
        return a;
    }

    public void setA(List<Author> a) {
        this.a = a;
    }
}
