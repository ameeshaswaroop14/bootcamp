import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Question12 {
    public static void main(String[] args)
    {
        List<Integer> list= Arrays.asList(7,4,8,9,11,45,98);
        System.out.println(
                list.stream()

                        .filter(e->e>3)
                        .filter(e->e%2==0)

                        .limit(1)
                        .collect(Collectors.toList())

        );

    }
}
