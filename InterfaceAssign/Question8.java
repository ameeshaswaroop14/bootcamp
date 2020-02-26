interface P1
{
    default  void show()
    {
        System.out.println("Display1");
    }
}
interface P2
{
    default  void show()
    {
        System.out.println("Display2");
    }
}
public class Question8 implements P1,P2 {
    public void show()
    {
        P1.super.show();
        P2.super.show();
    }

    public static void main(String[] args) {
        Question8 obj = new Question8();
        obj.show();
    }
}