package java2.question8;

import java.util.Scanner;

public class question8_do_while {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        do
        {
            String input = sc.nextLine();
            if(input.equalsIgnoreCase("done"))
            {
                System.out.println("Thank You");
                break;
            }
            else
            {
                int length = input.length();
                if(input.charAt(0)==input.charAt(length-1))
                {
                    System.out.println("First and Last character of "+input+" are same.");
                }
                else
                {
                    System.out.println("First and Last character of "+input+" are not same.");
                }
            }
        }
        while(true);
    }
}
