package MultiThreading;
// 1. Write a programe do to demonstrate the use of volatile keyword.
public class Q1 {

    private static volatile int MY_INT = 0;

    public static void main(String[] args) {
        ChangeListener cl=new ChangeListener();
        Thread t1=new Thread(cl);
        ChangeMaker cm=new ChangeMaker();
        Thread t2=new Thread(cm);
        t1.start();
        t2.start();
    }

    static class ChangeListener implements Runnable {
        @Override
        public void run() {
            int local_value = MY_INT;
            while ( local_value < 5){
                if( local_value!= MY_INT){
                    System.out.println("change in MY_INT"+MY_INT);
                    local_value= MY_INT;
                }
            }
        }
    }

    static class ChangeMaker implements Runnable{
        @Override
        public void run() {

            int local_value = MY_INT;
            while (MY_INT <5){
                System.out.println("Incrementing local: "+(local_value+1));
                MY_INT = ++local_value;
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) { e.printStackTrace(); }
            }
        }
    }
}