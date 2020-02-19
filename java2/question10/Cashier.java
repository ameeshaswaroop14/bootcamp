package java2.question10;

import java.util.ArrayList;
import java.util.List;

public class Cashier extends Order implements QueueOfPendingOrder {
    String name;
    QueueOfPendingOrder queueOfPendingOrder;
    List<Customer> customerList;

    public Cashier(String name,Long id) {
        super(id);
        this.name = name;
        customerList = new ArrayList<>();
    }

    String AcceptOrderAndAddCustomerToCustomerList(Customer customer,Order order,double cash) {
        customerList.add(customer);
        System.out.println("Accepted order");

        return "token";
    }

    void addOrderInOrderQueue(Order order){
        queueOfPendingOrder.add(order);
        System.out.println(order + " added to order queue");
    }
}