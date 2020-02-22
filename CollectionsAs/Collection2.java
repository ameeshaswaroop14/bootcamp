import java .util.*;
public class Collection2 {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        {
                System.out.println("Enter String");
                String s1=sc.nextLine();
                try {
                    HashSet<Object> h = new HashSet<Object>();
                    for (int i = 0; i < s1.length(); i++) {
                        h.add(s1.charAt(i));
                    }
                    Iterator<Object> itr = h.iterator();
                    while (itr.hasNext()) {
                        System.out.println(itr.next());
                    }
                } catch (Exception e) {
                    System.out.println("error");
                }
            }
            }}

