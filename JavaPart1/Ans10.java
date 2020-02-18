public class Ans10
{
    void add(int a,int b)
    {
        System.out.println("Sum of two numbers: "+a+b);
    }
    void add(double x1,double x2)
    {
        System.out.println("Sum of two double members is="+x1+x2);
    }
    void multiply(float a1,float b1)
    {
        float multi=a1*b1;
        System.out.println("Multiplication of two float numbers is="+multi);
    }
    void multiply(int a,int b)
    {
        int  multi1=a*b;
        System.out.println("Multiplication of two int numbers is="+multi1);
    }
    void concat(String x1,String x2)
    {
        System.out.println(x1+x2);
    }
    void concat(String x1,String x2,String x3)
    {
        System.out.println(x1+x2+x3);
    }
    public static void main(String args[]) {
        Ans10 obj = new Ans10();


        obj.add(2, 3);
        obj.add(5.0, 6.0);
        obj.multiply(5.0f, 6.0f);
        obj.multiply(3, 2);
        obj.concat("Ameesha ", "Swaroop");
        obj.concat("Chandler ", "Muriel ", "Bing ");
    }}
