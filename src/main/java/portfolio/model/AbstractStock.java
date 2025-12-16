package portfolio.model;
import portfolio.service.Stock;

import java.math.BigDecimal;
import java.math.RoundingMode;

public abstract class AbstractStock implements Stock {
    protected final String symbol;
    protected final BigDecimal price;
    protected final int quantity;

    protected AbstractStock(String symbol, BigDecimal price, int quantity) {
        this.symbol = symbol;
        this.price = price;
        this.quantity = quantity;
    }

    @Override public String symbol() { return symbol; }
    @Override public BigDecimal price() { return price; }
    @Override public int quantity() { return quantity; }

    @Override
    public BigDecimal marketValue() {
        return price.multiply(BigDecimal.valueOf(quantity)) .setScale(2, RoundingMode.HALF_UP);
    }



}