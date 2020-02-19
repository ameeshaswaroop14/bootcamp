package java2.question1;

public class BookItem {

    Author a;
    boolean isBlocked;
    String issuedOn;

    public String getIssuedOn() {
        return issuedOn;
    }

    public void setIssuedOn(String issuedOn) {
        this.issuedOn = issuedOn;
    }

    public String getExpiredOn() {
        return expiredOn;
    }

    public void setExpiredOn(String expiredOn) {
        this.expiredOn = expiredOn;
    }

    String expiredOn;

    public Author getA() {
        return a;
    }

    public boolean isBlocked() {
        return isBlocked;
    }



    public void setA(Author a) {
        this.a = a;
    }

    public void setBlocked(boolean blocked) {
        isBlocked = blocked;
    }
}
