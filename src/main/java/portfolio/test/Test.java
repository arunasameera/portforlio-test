package portfolio.test;

import portfolio.model.StockType;
import portfolio.service.Stock;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Test {



    public static void main(String[] args) {

        final List<Stock> stocks = new ArrayList<>();

        record Stock(String symbol, StockType type, BigDecimal price, int quantity) {
            BigDecimal marketValue() { return price.multiply(BigDecimal.valueOf(quantity)); }
        }
        enum StockType { COMMON, ETF, PREFERRED }



        List<Stock> findEtfs(List<Stock> stocks){
            return stocks.stream().filter(s -> s.type() == StockType.ETF)
                    .filter(s -> s.marketValue().compareTo(BigDecimal.valueOf(1000)) > 0)
                    .sorted()
        }

        public Map<StockType, BigDecimal> marketValueByType(List<Stock> stocks) {
            if (stocks == null) {
                throw new IllegalArgumentException("stocks cannot be null");
            }

            return stocks.stream()
                    .collect(Collectors.groupingBy(
                            Stock::type,
                            Collectors.reducing(
                                    BigDecimal.ZERO,
                                    Stock::marketValue,
                                    BigDecimal::add
                            )
                    ));
        }



    }


}
