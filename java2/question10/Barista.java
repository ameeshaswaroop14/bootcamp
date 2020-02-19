package java2.question10;

public class Barista implements QueueOfPendingOrder,QueueOfCompletedOrder {

    String name;
    QueueOfPendingOrder queueOfPendingOrder;
    QueueOfCompletedOrder queueOfCompletedOrder;

    @Override
    public void remove(Order order) {
        queueOfPendingOrder.remove(order);
        System.out.println(order + "removed from Pending queue");
    }

    public void makeCoffee(Order order){
        System.out.println("Making coffee for " + order);
    }

    public void notifyAboutCompletedOrder(Customer customer,Order order) {
        System.out.println(customer + "your order " + order + " has been completed");
    }

    @Override
    public void addToCompleteOrderQueue(Order order) {
        queueOfCompletedOrder.addToCompleteOrderQueue(order);
        System.out.println(order + " added to Completed queue");
    }
}