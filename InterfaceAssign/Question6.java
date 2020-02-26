interface DefaultInterface
{
    default void display(String s)
    {
        System.out.println("default method "+s);
    }

}
interface StaticInterface{
    static void display(String s)
    {
        System.out.println("static method "+s);
    }
}
public class Question6 implements DefaultInterface,StaticInterface {
    public static void main(String[] args) {
        Question6 obj = new Question6();
        obj.display("hey");
        StaticInterface.display("static");
    }
}
