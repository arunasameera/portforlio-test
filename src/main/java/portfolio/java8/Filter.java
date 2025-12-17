package portfolio.java8;

import java.util.List;

public class Filter {


    public static void main(String[] args) {

        List<Integer> numbers = List.of(10, 2, 3, 4, 5);


        int result = numbers.stream()
                .filter(n -> n % 5 == 0)
                .map(n -> n * 2)
                .reduce(0, (a, b) -> a + b);

        System.out.println(result);
    }


}
