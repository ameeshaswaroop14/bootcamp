package java2.question9;

public class Main {
    public static void main(String[] args) {
        chair ch = new chair("wooden", true, true);
        table tb = new table("metal", true, true);
        System.out.println("Chair");
        System.out.println(ch.type);
        System.out.println(ch.checkStress());
        System.out.println(ch.checkfire());
        System.out.println("*********************************");
        System.out.println("Table");
        System.out.println(tb.type);
        System.out.println(tb.checkfire());
        System.out.println(tb.checkStress());
    }
}
