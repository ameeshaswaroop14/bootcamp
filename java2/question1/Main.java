package java2.question1;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Author author = new Author();
        author.be.setName("Shivam");
        author.be.setId(1);
        author.be.setCategory(Category.AUTHOR);
        author.resetPassword();

        Author author2 = new Author();
        author2.be.setName("Arora");
        author2.be.setId(2);
        author2.be.setCategory(Category.AUTHOR);
        author2.resetPassword();

        Librarian l = new Librarian();
        l.be.setId(100);
        l.be.setName("Ram Prasad");
        l.be.setCategory(Category.LIBRARIAN);

        List<Author> list = new ArrayList<>();
        list.add(author);
        list.add(author2);

        int isbn = 123456;
        BaseEntity be =new BaseEntity();
        be.setCategory(Category.FICTION);
        be.setName("asyoulikeit");
        be.setId(123);

        Book asyoulikeit = new Book() {
            @Override
            public void setBe(BaseEntity be) {
                super.setBe(be);
            }

            @Override
            public void setIsbn(int isbn) {
                super.setIsbn(isbn);
            }

            @Override
            public void setA(List<Author> list) {
                super.setA(list);
            }
        };
    }

}
