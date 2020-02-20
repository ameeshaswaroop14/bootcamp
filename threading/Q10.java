package MultiThreading;

public class Q10 {
    final Object lock = new Object();
    final Object lock1 = new Object();
    Thread t1 = new Thread() {
        public void run() {
            while (true) {
                synchronized (lock) {
                    System.out.println("acquired lock");
                    try {
                        Thread.sleep(1000);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    System.out.println("waiting for lock1");
                    synchronized (lock1) {
                        System.out.println("hello here");
                    }
                }
            }
        }

    };
    Thread t2 = new Thread() {
        public void run() {
            while (true) {
                synchronized (lock1) {

                    System.out.println("acquired lock1");

                    try {
                        Thread.sleep(1000);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    System.out.println("waiting for lock");
                    synchronized (lock) {
                        System.out.println("hey");
                    }
                }
            }
        }
    };

    public static void main(String[] args) {
        Q10 q = new Q10();
        q.t1.start();
        q.t2.start();
    }
}
