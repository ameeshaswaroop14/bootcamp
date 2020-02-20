package MultiThreading;
// Write a program to demonstrate sleep and join methods.
public class Q6_join {
    public static void main(String[] args) throws InterruptedException {
        joinClass jc=new joinClass();
        jc.start();
        jc.join(1000);
        System.out.println("joins after 1000ms");
        System.out.println("this threads name"+jc.getName());
        System.out.println("check if alive: "+jc.isAlive());
    }
}
class joinClass extends Thread{
    @Override
    public void run() {
        joinClass jc1=new joinClass();
        System.out.println("Current thread: "+jc1.getName());
        System.out.println("Check if it is alive: "+jc1.isAlive());
    }
}
