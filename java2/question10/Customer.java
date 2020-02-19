package java2.question10;

public class Customer {
    private String name;
    private String token;
    Cashier cashier;
    Order order;
    double amount;

    void placeOrder() {
        token = cashier.AcceptOrderAndAddCustomerToCustomerList(this,order,amount);
        System.out.println("This is the order token: " + token);
    }

    boolean waitingState(){
        System.out.println("Customer" + this.name + "is waiting");
        return true;
    }

    boolean drinkingState() {
        System.out.println("Customer " + this.name + " has collected coffee");
        return true;
    }
}