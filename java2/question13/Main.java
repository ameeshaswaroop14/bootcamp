package java2.question13;

import java.util.Scanner;

public class Main {
    public static void main(String[] args)throws MyException {
        Scanner sc = new Scanner(System.in);
        int a = sc.nextInt();
        try
        {
            if(a==0)
            {
                throw new MyException("This is my exception");
            }
        }
        catch(MyException e)
        {
            System.out.println(e.getMessage());
        }
    }
}
