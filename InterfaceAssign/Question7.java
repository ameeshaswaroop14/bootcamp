interface DefaultInterfacee
{
    default void display()
    {
        System.out.println("Default Interface");
    }
}
public class Question7 implements DefaultInterface{
    @Override
    public void display(String s) {

    }

    public void display() {
        System.out.println("Overriding default interface ");
    }

    public static void main(String[] args) {
        Question7 obj = new Question7();
        obj.display();

    }
}