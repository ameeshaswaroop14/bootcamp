import java.util.Scanner;


    import java.util.Scanner;
    public class Question1D {
        public static void main(String[] args) {
            Scanner sc=new Scanner(System.in);
            System.out.println("Enter  string");
            String a=sc.next();
            uppercase obj=(c) -> c.toUpperCase();
            System.out.println(obj.convert(a));

        }
    }
    interface uppercase
    {
        String convert(String a);
    }

