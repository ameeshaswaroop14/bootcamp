import java.util.Scanner;

interface oneNum
{
    int returnOne(int x,int y);
}
public class Question2 {
    public static void main(String[] args) {
        Scanner sc= new Scanner(System.in);
        System.out.println("Enter first number");
        int a=sc.nextInt();
        System.out.println("Enter second number");
        int b=sc.nextInt();
        oneNum obj=(int x,int y) ->
        {
            return x+y;
        };
        System.out.println(obj.returnOne(a,b));
    }
}
