import java.util.Scanner;

public class Question4J {
    public static void main(String args[])
    {
        Scanner sc=new Scanner(System.in);
        System.out.println("enter string");
        String s=sc.nextLine();
        s=s.trim();
        int len=s.length();
        int cu=0,cl=0,cd=0,cw=0,cs=0;
        double p1,p2,p3,p4;
        for(int x=0;x<len;x++)
        {
            char ch=s.charAt(x);
            if(Character.isUpperCase(ch))
            {
                cu++;
            }
           else if(Character.isLowerCase(ch))
            {
                cl++;
            }
           else  if(Character.isDigit(ch))
            {
                cd++;
            }
            else if(Character.isWhitespace(ch))
            {
                cw++;
            }

            else
            {
                cs++;
            }
        }

        p1=(cu/len)*100;
        p2=(cl/len)*100;
        p3=(cd/len)*100;
        p4=(cs/len)*100;

        System.out.println("uppercase "+p1);
        System.out.println("lowerCase "+p2);
        System.out.println("Digits "+cd);
        System.out.println("Special "+cs);


    }

}
