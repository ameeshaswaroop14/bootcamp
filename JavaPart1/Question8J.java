import java.util.*;
public class Question8J {
    public static void main(String[] args) {


        StringBuffer sb = new StringBuffer();
        Scanner input = new Scanner(System.in);
        System.out.println("Enter a string");
        sb.append(input.nextLine());
        int l=sb.length();
         sb.reverse();
         sb.delete(4,9);
         System.out.println(sb);

    }
}
