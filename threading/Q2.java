package MultiThreading;
//Write a program to create a thread using Thread class and Runnable interface each.


public class Q2 {
    public static void main(String[] args) {
    ThreadClass tc=new ThreadClass();
    tc.start();
    RunnableClass rc=new RunnableClass();
    Thread t=new Thread(rc);
    t.start();
    }
}
class ThreadClass extends Thread{

    @Override
    public void run() {
        for(int i=0;i<20;i+=2){
            System.out.print(i+". ");
            try {
                Thread.sleep(400);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println();
    }
}

class RunnableClass implements Runnable{

    @Override
    public void run() {
        for(int i=1;i<20;i+=2){
            System.out.print(i+" ");
            try {
                Thread.sleep(600);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
