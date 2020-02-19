package java2.question10;

import java.util.PriorityQueue;
import java.util.Queue;

interface QueueOfCompletedOrder {
    default void addToCompleteOrderQueue(Order order){
        Queue<Order> queue = new PriorityQueue<>();
    }

}

