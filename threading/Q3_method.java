package MultiThreading;
// 4. Write a program using synchronization block and synchronization method
public class Q3_method {
    public static int i=0;
    public static void main(String[] args) throws InterruptedException{
        Sync obj=new Sync();
        Testing t1=new Testing(obj);
        Testing t2=new Testing(obj);
        t1.start();
        t2.start();
    }
}
class Sync {
       synchronized void Synching(){
        for(Q3_method.i=0; Q3_method.i<10; Q3_method.i++){
            System.out.println("i= "+ Q3_method.i);
        }
        try {
            Thread.sleep(400);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
class Testing extends Thread{
    Sync sync;
    Testing(Sync sync){
        this.sync=sync;
    }
    public void run(){
        sync.Synching();
    }
}


