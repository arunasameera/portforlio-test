package portfolio.java8;

import java.math.BigDecimal;
import java.util.List;

public class ReduceBigDecimal {


    public static void main(String[] args) {

        List<BigDecimal> values = List.of(
                new BigDecimal("10.50"),
                new BigDecimal("20.25"),
                new BigDecimal("5.25")
        );

        BigDecimal total = values.stream()
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        System.out.println(total); // 36.00

    }


}
