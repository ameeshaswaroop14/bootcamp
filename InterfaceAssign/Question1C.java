
import java.util.Scanner;
public class Question1C {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter first string");
        String a=sc.next();
        System.out.println("Enter second string");
        String b=sc.next();
        Concatstr concatstr=(c, d) -> c+d;
        System.out.println(concatstr.conc(a,b));

    }
}
interface Concatstr
{
    String conc(String a, String b);
}
