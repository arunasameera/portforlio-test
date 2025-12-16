package portfolio.model;

import java.math.BigDecimal;
public class Etf extends AbstractStock {
    private final String indexName;
    public Etf(String symbol, BigDecimal price, int quantity, String indexName) {
        super(symbol, price, quantity);
        this.indexName = indexName;
    }
    public String indexName() { return indexName; }
    @Override public StockType type() { return StockType.ETF; }
}