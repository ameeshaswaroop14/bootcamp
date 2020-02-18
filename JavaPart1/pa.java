public class pa {
    public static void main(String args[]) {


        StringBuffer s = new StringBuffer("abc");
        System.out.println((s.hashCode()));
        s.append("xyz");
        System.out.println(s);
        System.out.println(s.hashCode());

    }
}