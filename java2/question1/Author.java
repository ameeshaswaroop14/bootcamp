package java2.question1;

public class Author extends Account {
    BaseEntity be;

    public BaseEntity getBe() {
        return be;
    }

    public void setBe(BaseEntity be) {
        this.be = be;
    }

    @Override
    public boolean resetPassword() {
        return true;
    }
}
