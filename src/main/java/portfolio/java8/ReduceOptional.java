package portfolio.java8;

import java.util.List;
import java.util.Optional;

public class ReduceOptional {


    public static void main(String[] args) {

        List<Integer> numbers = List.of(1, 2, 3, 4, 5);


        Optional<Integer> sum = numbers.stream()
                .reduce((a, b) -> a + b);

        sum.ifPresent(System.out::println);


        Optional<Integer> multiply =
                numbers.stream()
                        .reduce((a, b) -> a * b);

        multiply.ifPresent(System.out::println);


        Optional<Integer> max =
                numbers.stream()
                        .reduce(Integer::max);

        max.ifPresent(System.out::println);

    }


}
