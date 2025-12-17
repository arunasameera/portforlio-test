package portfolio.java8;

import java.util.List;
import java.util.Optional;

public class FindFirst {


    public static void main(String[] args) {

        List<Integer> numbers = List.of(10, 2, 3, 4, 5);


        int  result = numbers.stream()
                .filter(n -> n % 5 == 0)
                .map(n -> n * 2)
                .findFirst()
                        .orElse(0);

        System.out.println(result);
    }


}
