package portfolio;
import org.junit.jupiter.api.Test;
import portfolio.model.CommonStock;
import portfolio.model.Etf;
import portfolio.model.PreferredStock;
import portfolio.model.StockType;
import portfolio.service.Portfolio;
import portfolio.service.Stock;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PortfolioTest {
    @Test
    void testMarketValue() {
        Stock s = new CommonStock("AAPL", new BigDecimal("10"), 5);
        assertEquals(0, new BigDecimal("50").compareTo(s.marketValue()));
    }


    @Test
    public void findByType_returnsOnlyEtfs(){

        Portfolio portfolio = new Portfolio();
        portfolio.add(new CommonStock("AAPL", new BigDecimal("10"), 5));
        portfolio.add(new PreferredStock("GOOGL", new BigDecimal("20"), 2, new BigDecimal("0.05")));
        portfolio.add(new CommonStock("MSFT", new BigDecimal("15"), 3));
        portfolio.add(new Etf("SPY", new BigDecimal("30"), 1, "S&P 500"));


        List<Stock> etfStocks = portfolio.findByType(StockType.ETF);
        assertEquals(1, etfStocks.size());
        assertEquals(StockType.ETF, etfStocks.get(0).type());
        assertEquals("SPY", etfStocks.get(0).symbol());

        Etf etf = (Etf) etfStocks.get(0);
        assertEquals("S&P 500", etf.indexName());
    }




    @Test
    public void totalMarketValue_returnsCorrectSum(){

        Portfolio portfolio = new Portfolio();
        portfolio.add(new CommonStock("AAPL", new BigDecimal("10"), 5)); // 50
        portfolio.add(new PreferredStock("GOOGL", new BigDecimal("20"), 2, new BigDecimal("0.05"))); // 40
        portfolio.add(new CommonStock("MSFT", new BigDecimal("15"), 3)); // 45
        portfolio.add(new Etf("SPY", new BigDecimal("30"), 1, "S&P 500")); // 30

        BigDecimal totalValue = portfolio.totalMarketValue();

        assertEquals(0, new BigDecimal("165").compareTo(totalValue));

    }

    @Test
    public void findByType_throwsException_whenTypeIsNull(){
        Portfolio portfolio = new Portfolio();

        assertEquals(IllegalArgumentException.class,
                assertThrows(IllegalArgumentException.class,
                        () -> portfolio.findByType(null)).getClass());

    }

    @Test
    public void topNList_returnsTopNStocksByMarketValue(){

        Portfolio portfolio = new Portfolio();
        portfolio.add(new CommonStock("AAPL", new BigDecimal("10"), 5)); // 50
        portfolio.add(new PreferredStock("GOOGL", new BigDecimal("20"), 2, new BigDecimal("0.05"))); // 40
        portfolio.add(new CommonStock("MSFT", new BigDecimal("15"), 3)); // 45
        portfolio.add(new Etf("SPY", new BigDecimal("30"), 1, "S&P 500")); // 30

        List<Stock> top2Stocks = portfolio.topNList(2);

        assertEquals(2, top2Stocks.size());
        assertEquals("AAPL", top2Stocks.get(0).symbol()); // 50
        assertEquals("MSFT", top2Stocks.get(1).symbol()); // 45

    }


    @Test
    public void find_withAllCreteriaValues(){
        Portfolio portfolio = new Portfolio();
        portfolio.add(new CommonStock("AAPL", new BigDecimal("10"), 5)); // 50
        portfolio.add(new PreferredStock("GOOGL", new BigDecimal("20"), 2, new BigDecimal("0.05"))); // 40
        portfolio.add(new CommonStock("MSFT", new BigDecimal("15"), 3)); // 45
        portfolio.add(new Etf("SPY", new BigDecimal("30"), 1, "S&P 500")); // 30

        var criteria = new portfolio.model.StockCriteria(
                StockType.COMMON,
                new BigDecimal("40"),
                "A"
        );

        List<Stock> result = portfolio.find(criteria);

        assertEquals(1, result.size());
        assertEquals("AAPL", result.get(0).symbol());

        CommonStock commonStock = (CommonStock) result.get(0);
        assertEquals(StockType.COMMON,commonStock.type());
        assertEquals(0, new BigDecimal("50").compareTo(commonStock.marketValue()));
        assertEquals("AAPL", commonStock.symbol());
    }

    @Test
    public void find_throwsException_whenCriteriaIsNull(){
        Portfolio portfolio = new Portfolio();

        assertEquals(IllegalArgumentException.class,
                assertThrows(IllegalArgumentException.class,
                        () -> portfolio.find(null)).getClass());

    }

}