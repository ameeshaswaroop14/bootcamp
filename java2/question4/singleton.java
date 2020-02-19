// WAP to create singleton class.
package java2.question4;

public class singleton {
    private static singleton single_instance = null;
    public  String s;
    private singleton()
    {
        System.out.println("This is the singleton class");
    }
    public static singleton getInstance()
    {
        if (single_instance == null)
            single_instance = new singleton();

        return single_instance;
    }
}
