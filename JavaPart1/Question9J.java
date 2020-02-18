

class test{
    enum Status {
        House(1000),
        Hut(1500),
        Hotel(2500),
        Building(1300),
        Farmhouse(1800),
        Barn(2700),
        Lighthouse(9000);

        private int value;

        Status(int value) {
            this.value = value;
        }

        public int getPrice() {
            return this.value;
        }
    }
    int p;
    test(Status v) {
        Status myVar = v;

        switch (myVar) {
            case House:
                System.out.println("Price is : " + myVar.getPrice());
                break;
            case Hut:
                System.out.println("Price is : " + myVar.getPrice());
                break;
            case Hotel:
                System.out.println("Price is : " + myVar.getPrice());
                break;
            case Building:
                System.out.println("Price is : " + myVar.getPrice());
                break;
            default:
                System.out.println("404 not found");
                break;
        }
    }
}
public class Question9J{
    public static void main(String[] args) {
        test obj=new test(test.Status.Hut);
    }
}