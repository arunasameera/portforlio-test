package portfolio;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import portfolio.model.CommonStock;
import portfolio.model.Etf;
import portfolio.model.PreferredStock;
import portfolio.model.StockType;
import portfolio.service.Portfolio;
import portfolio.service.Stock;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PortfolioTestParameterized {

    private static Portfolio portfolioWithSampleData() {
        Portfolio p = new Portfolio();

        // market values:
        // AAPL 100*10 = 1000
        // IVV  500*30 = 15000
        // PREF 200*20 = 4000
        // AMZN 300*50 = 15000
        p.add(new CommonStock("AAPL", new BigDecimal("100"), 10));
        p.add(new Etf("IVV", new BigDecimal("500"), 30));
        p.add(new PreferredStock("PREF", new BigDecimal("200"), 20));
        p.add(new CommonStock("AMZN", new BigDecimal("300"), 50));

        return p;
    }

    @ParameterizedTest(name = "{index} - {0}")
    @MethodSource("criteriaCases")
    void find_filtersCorrectly(String caseName,
                               StockCriteria criteria,
                               List<String> expectedSymbolsInOrder) {

        Portfolio p = portfolioWithSampleData();

        List<String> actualSymbols = p.find(criteria).stream()
                .map(Stock::symbol)
                .toList();

        assertEquals(expectedSymbolsInOrder, actualSymbols);

        // Optional: assert result is unmodifiable (because you used toUnmodifiableList)
        List<Stock> result = p.find(criteria);
        assertThrows(UnsupportedOperationException.class,
                () -> result.add(new CommonStock("TSLA", new BigDecimal("1"), 1)));
    }

    static Stream<Arguments> criteriaCases() {
        return Stream.of(
                Arguments.of(
                        "type = ETF only",
                        new StockCriteria(StockType.ETF, null, null),
                        List.of("IVV")
                ),
                Arguments.of(
                        "minValue >= 10000 only",
                        new StockCriteria(null, new BigDecimal("10000"), null),
                        List.of("IVV", "AMZN")
                ),
                Arguments.of(
                        "symbolPrefix = 'AA' only",
                        new StockCriteria(null, null, "AA"),
                        List.of("AAPL")
                ),
                Arguments.of(
                        "type = COMMON and minValue >= 10000",
                        new StockCriteria(StockType.COMMON, new BigDecimal("10000"), null),
                        List.of("AMZN")
                ),
                Arguments.of(
                        "type = COMMON and prefix = 'A'",
                        new StockCriteria(StockType.COMMON, null, "A"),
                        List.of("AAPL", "AMZN")
                ),
                Arguments.of(
                        "no filters (nulls/blank) => all",
                        new StockCriteria(null, null, ""),
                        List.of("AAPL", "IVV", "PREF", "AMZN")
                ),
                Arguments.of(
                        "no matches example",
                        new StockCriteria(StockType.PREFERRED, new BigDecimal("999999"), "X"),
                        List.of()
                )
        );
    }

    @Test
    void find_throwsIfCriteriaIsNull() {
        Portfolio p = portfolioWithSampleData();
        assertThrows(IllegalArgumentException.class, () -> p.find(null));
    }

}