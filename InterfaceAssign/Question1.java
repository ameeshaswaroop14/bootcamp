import java.util.*;

public class Question1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter First number");
        int a=sc.nextInt();
        System.out.println("Enter second number");
        int b=sc.nextInt();
        greater obj=(x,y)->
        {
            if(x>y)
                return true;
            else
                return false;
        };
        System.out.println(obj.cal(a,b));


    }

}
interface greater
{
    boolean cal(int a,int b);
}

