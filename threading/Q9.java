package MultiThreading;

import java.util.concurrent.CountDownLatch;

public class Q9 {
    public static void main(String args[]) {
        CountDownLatch countDownLatch = new CountDownLatch(2);
        Abc n = new Abc("thread1", countDownLatch);
        Abc n1 = new Abc("thread2", countDownLatch);
        Thread t1 = new Thread(n);
        Thread t2 = new Thread(n1);
        t1.start();
        t2.start();

        try {
            countDownLatch.await();
            System.out.println("All threads are running");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class Abc implements Runnable {
    private String name;
    private CountDownLatch countDownLatch;

    public Abc(String name, CountDownLatch countDownLatch) {
        this.name = name;
        this.countDownLatch = countDownLatch;
    }

    public void run() {
        try {
            System.out.println(name + " started. ");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
        System.out.println(name + " is Up and running.");
        countDownLatch.countDown();
    }
}
