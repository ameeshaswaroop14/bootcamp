/*
WAP to sorting string without using string Methods?.
*/
package java2.question2;

import java.util.Arrays;

public class question2 {
    public static void main(String[] args) {
        String given = "dcba";
        char c[] = given.toCharArray();
        Arrays.sort(c);
        String output = new String(c);
        System.out.println(output);
    }
}
