package com.SpringAssign.demoSpring;


class First
{

    void display()
    {
        System.out.println("First");
    }
}
class Second
{
    void display()
    {
        System.out.println("Second");
    }
}
public class TightlyCoupled
{
    First first = new First();
    void show()
    {
        first.display();
    }
    public static void main(String[] args) {


        TightlyCoupled tightlyCoupled = new TightlyCoupled();
        tightlyCoupled.show();

    }
}

