package java2.question1;

public class Librarian extends Account{
    BaseEntity be;

    public void setBe(BaseEntity be) {
        this.be = be;
    }

    public BaseEntity getBe() {
        return be;
    }
    public boolean isBlocked(BookItem bookItem)
    {
        return bookItem.isBlocked;
    }

    @Override
    public boolean resetPassword() {
        return true;
    }
}
