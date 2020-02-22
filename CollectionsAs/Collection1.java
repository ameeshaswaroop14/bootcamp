import java.util.*;

public class Collection1 {
    public static void main (String [] args) {
        Scanner sc = new Scanner(System.in);
        int n = 5, i;
        float sum = 0.0f;

        //declaring ArrayList with initial size n
        ArrayList<Float> arrli = new ArrayList<Float>(n);

        // Appending the new element at the end of the list
        System.out.println("Please enter a list of numbers: ");
        for (i = 0; i < n; i++) {

                float input = sc.nextFloat();
                arrli.add(input);
            }

            // Printing elements
            System.out.println(arrli);
            Iterator iterator = arrli.iterator();

            //stem.out.println("List elements : ");

            while (iterator.hasNext())
            //for(i=0;i<n;i++)
            {
                sum = sum + arrli.get(i);
            }
            System.out.print(sum + " ");

        }
    }