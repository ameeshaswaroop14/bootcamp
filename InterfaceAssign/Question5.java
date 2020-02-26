import java.util.stream.Collectors;

//Implement following functional interfaces from java.util.function using lambdas:
//
//(1) Consumer
//
//(2) Supplier
//
//(3) Predicate
//
//(4) Function

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
public class Question5 {
    public static void main(String[] args)
    {
        Consumer<Integer> consumer=n->System.out.println(n);
        consumer.accept(10);
        Supplier<String>  stringSupplier=()->"This is supplier";
        System.out.println(stringSupplier.get());
        Predicate<Integer> predicate=n->n%2==0;
        System.out.println(predicate.test(35));
        Function<Integer,Integer> function=n->n*6;
        System.out.println(function.apply(20));
    }
}
