package java2.question1;

import java.util.ArrayList;
import java.util.List;

public class user extends Account {
    BaseEntity be;
    List<BookItem> a = new ArrayList<>();

    public BaseEntity getBe() {
        return be;
    }

    public void setBe(BaseEntity be) {
        this.be = be;
    }

    public List<BookItem> getA() {
        return a;
    }

    public void setA(List<BookItem> a) {
        this.a = a;
    }

    @Override
    public boolean resetPassword() {
        return true;
    }
}
