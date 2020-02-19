package java2.question4;

public class Main {
    public static void main(String[] args) {
        singleton x = singleton.getInstance();
        singleton y = singleton.getInstance();
        singleton z = singleton.getInstance();
    }
}
