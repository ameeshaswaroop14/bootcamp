package MultiThreading;
//4. Write a program to demonstrate wait and notify methods.
public class Q5 {
    public static void main(String[] args) throws InterruptedException {
        Summ s1=new Summ();
        Thread t=new Thread(s1);
        t.start();
        synchronized (t){
            try{
                System.out.println("wait till Summ class finishes");
                t.wait();
                Thread.sleep(400);
            }
            catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        System.out.println("total is: "+s1.sum);
    }

}
class Summ implements Runnable{
    int sum=0;
    @Override
    public void run() {
        synchronized (this){
            for(int i=0;i<100;i++)
                sum+=i;
            notify();
        }
    }
}