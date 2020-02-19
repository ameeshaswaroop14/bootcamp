package java2.question5;

public class question5 {
    String fName;
    String lName;

    public question5(String f, String l)
    {
        this.fName = f;
        this.lName = l;
    }
    public question5(question5 obj)
    {
        fName = obj.fName;
        lName = obj.lName;
    }
}
