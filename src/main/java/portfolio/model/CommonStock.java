package portfolio.model;

import java.math.BigDecimal;
public class CommonStock extends AbstractStock {
    public CommonStock(String symbol, BigDecimal price, int quantity) {
        super(symbol, price, quantity);
    }
    @Override public StockType type() { return StockType.COMMON; }
}