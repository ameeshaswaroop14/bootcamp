package java2.question10;

import java.util.PriorityQueue;
import java.util.Queue;

public interface  QueueOfPendingOrder {
    Queue<Order> queue = new PriorityQueue<>();
    default void add(Order order){
        queue.add(order);
    }
    default void remove(Order order) {
        queue.remove(order);
    }
}