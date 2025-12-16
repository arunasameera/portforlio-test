package portfolio.model;

import java.math.BigDecimal;
public class PreferredStock extends AbstractStock {
    private final BigDecimal dividendYield;
    public PreferredStock(String symbol, BigDecimal price, int quantity, BigDecimal dividendYield) {
        super(symbol, price, quantity);
        this.dividendYield = dividendYield;
    }
    public BigDecimal dividendYield() { return dividendYield; }
    @Override public StockType type() { return StockType.PREFERRED; }
}