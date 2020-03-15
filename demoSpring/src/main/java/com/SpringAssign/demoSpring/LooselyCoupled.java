package com.SpringAssign.demoSpring;

public class LooselyCoupled {
    public static void main(String[] args)
    {   Manage manage = new Manage();
        First1 first1 = new First1();
        manage.setDisplay(first1);
        manage.showD();
        //Second1 second1 = new Second1();


    }
}
class Manage{
    Display display;

    public Display getDisplay() {
        return display;
    }

    public void setDisplay(Display display) {
        this.display = display;
    }


    public void showD()
    {
    display.show();
    }
}
interface Display{
    void show();
}
class First1 implements Display{


    @Override
    public void show() {
        System.out.println("First1");

    }
}
class Second1 implements Display{

    @Override
    public void show() {
        System.out.println("Second1");

    }
}
