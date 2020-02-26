

import java.util.Scanner;
interface Operations
{
   int operations(int a,int b);
}

public class Question3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter First number");
        int a = sc.nextInt();
        System.out.println("Enter second number");
        int b = sc.nextInt();
        Operations ob=new Question3()::add;
        System.out.println(ob.operations(a,b));
        Operations ob1=new Question3()::sub;
        System.out.println(ob1.operations(a,b));
        Operations ob2=Question3::multiply;
        System.out.println(ob2.operations(a,b));



    }

        int  add(int a,int b)
        {
            return a+b;

        }
        int sub(int a,int b) {

            return a - b;
        }
        static int multiply(int a,int b)
    {
        return a*b;
    }



    }


