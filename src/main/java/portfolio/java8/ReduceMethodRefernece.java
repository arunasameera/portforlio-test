package portfolio.java8;

import java.util.List;

public class ReduceMethodRefernece {


    public static void main(String[] args) {

        List<Integer> numbers = List.of(1, 2, 3, 4, 5);


        int sum = numbers.stream()
                .reduce(0, Integer::sum);
        System.out.println(sum);


        int multiply = numbers.stream()
                .reduce(1,Math::multiplyExact);
        System.out.println(multiply);



        int max = numbers.stream()
                .reduce(Integer.MIN_VALUE, Integer::max);

        System.out.println(max);
    }


}
