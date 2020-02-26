import java.util.Scanner;

public class Question1B {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter Number");
        int a=sc.nextInt();
        Increment increment=(x) -> x+1;
        System.out.println(increment.calculate( a));
    }
}
interface Increment
{
    int calculate(int a);
}
