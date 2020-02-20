package MultiThreading;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class Q4 {

    public static int number;
    public static void main(String[] args){
        final int max = 10;

        Executor executor = Executors.newFixedThreadPool(2);

        final Object lock = new Object();

        executor.execute(new Runnable() {
            @Override
            public synchronized void run() {
                while (number < max) {
                    if (number % 2 == 0) {
                        number = number+1;
                        System.out.println(Thread.currentThread().getName()+" "+number+" ");


                        synchronized(lock){
                            lock.notify();
                        }
                    }else{
                        synchronized(lock){
                            try {
                                lock.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        });
        executor.execute(new Runnable() {
            @Override
            public synchronized void run() {
                     try {
                         Thread.sleep(500);
                     }catch (Exception e)
                     {
                         e.printStackTrace();
                     }
                while (number < max) {
                    if (number% 2 != 0) {
                        number++;
                        System.out.println(Thread.currentThread().getName()+" "+number+" ");

                        synchronized(lock){
                            lock.notify();
                        }
                    }else{
                        synchronized(lock){
                            try {
                                lock.wait();
                            } catch (InterruptedException e) {

                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        });
        do {
            try {
                Thread.currentThread().sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } while (number!= max);
        System.out.println("\nDone");
    }
}