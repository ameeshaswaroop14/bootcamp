

import java.util.*;

public class Collection8
{
    static int top=-1;
    static int size=5;
    static int top_one=-1;
    static int i;
    List<Integer> list = new ArrayList<>();
    List<Integer> minlist = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);
    public void push(int x)
    {
        if (isFull())
            System.out.println("stack is already full,cannot add more elements");
        else {
            top++;
            list.add(top,x);
            System.out.println(x+" is added to the stack");
            if (isMinListEmpty())
            {
                top_one++;
                minlist.add(top_one,x);
            }
            else
            {
                if(minlist.get(top_one)>=x)
                {
                    top_one++;
                    minlist.add(top_one,x);
                }
            }
        }
    }
    public void pop()
    {
        if (isEmpty())
        {
            System.out.println("stack is already empty,cannot remove elements");
        }
        else
        {
            if(list.get(top)==minlist.get(top_one))
            {

                minlist.remove(top_one);
                top_one--;
            }
            System.out.println(list.get(top)+" is removed from the stack");
            list.remove(top);
            top--;

        }

    }
    public void peek()
    {
        if (isEmpty())
        {
            System.out.println("stack is empty,no elements to display");
        }
        else
        {
            System.out.println("top element is "+list.get(top));
        }
    }
    public boolean isEmpty()
    {
        if(top==-1)
            return true;
        else
            return false;
    }
    public boolean isFull()
    {
        if (top==size-1)
            return true;
        else
            return false;
    }

    public boolean isMinListEmpty()
    {
        if(top_one==-1)
            return true;
        else
            return false;
    }
    public void getMinimun()
    {
        if(minlist.isEmpty())
        {
            System.out.println("stack is already empty");

        }
        else {
            System.out.println("minimum element is "+minlist.get(top_one));
        }
    }

    public void menu() {
        do {
            System.out.println("Enter 1 to Push: ");
            System.out.println("Enter 2 to Pop: ");
            System.out.println("Enter 3 for isEmpty : ");
            System.out.println("Enter 4 for isFull: ");
            System.out.println("Enter 5 to getMin: ");
            System.out.println("Enter 6 for peek:");
            System.out.println("Enter any to break: ");
            System.out.print("Enter value: ");
            i = sc.nextInt();

            sc.nextLine();

            switch (i) {
                case 1:
                    System.out.print("Enter value to push: ");
                    int x = sc.nextInt();
                    push(x);
                    break;
                case 2:
                    pop();
                    break;
                case 3:
                    if (isEmpty())
                        System.out.println("stack is empty");
                    else System.out.println("stack is not empty");
                    break;
                case 4:
                    if (isFull())
                        System.out.println("stack is full");
                    else System.out.println("stack is not full");
                    break;
                case 5:
                    getMinimun();
                    break;
                case 6:
                    peek();
                    break;
                default:
                    break;
            }

        }while(i<7);
    }
    public static void main (String[]args)
    {
        Collection8 q = new Collection8();
        System.out.println("enter size of the stack");
        size = sc.nextInt();
        q.menu();
    }

}

