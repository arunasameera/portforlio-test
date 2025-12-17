package portfolio.java8;

import java.util.List;
import java.util.stream.Collectors;

public class Map {


    public static void main(String[] args) {

        List<Integer> numbers = List.of(1, 2, 3, 4, 5);


        List result = numbers.stream()
                .map(n -> n * 2)
                .collect(Collectors.toList());

        System.out.println(result);
    }


}
