package MultiThreading;

import com.sun.org.apache.xalan.internal.res.XSLTErrorResources;

public class Q6_Sleep {
    public static void main(String[] args) {
        SleepClass sc1=new SleepClass();
        SleepClass sc2=new SleepClass();
        sc1.start();;
        sc2.start();
    }
}
class SleepClass extends Thread{
    @Override
    public void run() {
        SleepClass sc=new SleepClass();
        try{
            for(int i=0;i<10;i++){
                System.out.println("Thread name: "+sc.getName()+" "+i);
                sc.sleep(1000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}