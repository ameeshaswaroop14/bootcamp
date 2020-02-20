package MultiThreading;
// 4. Write a program using synchronization block and synchronization method
public class Q3_block {
    public static int i=0;
    public static void main(String[] args) {
        SyncBlock obj=new SyncBlock();
        TestingBlock t1=new TestingBlock(obj);
        TestingBlock t2=new TestingBlock(obj);
        t1.start();
        t2.start();

    }
}
class SyncBlock {
    void Synching(){
      synchronized (this) {
           for(Q3_block.i=0; Q3_block.i<10; Q3_block.i++){
               System.out.println("i= "+ Q3_block.i);
           }
           try {
               Thread.sleep(400);
           } catch (InterruptedException e) {
               e.printStackTrace();
           }
       }
    }
}
class TestingBlock extends Thread{
    SyncBlock sync;
    TestingBlock(SyncBlock sync){
        this.sync=sync;
    }
    public void run(){
        sync.Synching();
    }
}
