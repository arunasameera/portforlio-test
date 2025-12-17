package portfolio.java8;

import java.util.List;

import static java.lang.System.out;

public class Java8Demo {

    public static void main(String[] args) {


        List<Integer> numbers = List.of(1, 2, 3, 4, 5);


        numbers.forEach(n -> out.println(n) );
        numbers.forEach(Java8Demo::doubleValue);

    }


    public static void  doubleValue(int n) {
        System.out.println(n * 2);
    }


}
