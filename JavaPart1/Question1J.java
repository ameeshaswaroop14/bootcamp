import java.util.Scanner;

public class Question1J {
    public static void main(String args[])
    {
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter String");
        String S=sc.nextLine();
        int len=S.length();
        System.out.println("Enter substring to be replaced");
        String S2=sc.nextLine();
        System.out.println("Enter String to be replaced with");
        String S3=sc.nextLine();
        String res=S.replace(S2,S3);
        System.out.println((res));

    }

}
