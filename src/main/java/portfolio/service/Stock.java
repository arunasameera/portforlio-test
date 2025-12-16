package portfolio.service;
import portfolio.model.StockType;

import java.math.BigDecimal;
public interface Stock {
    String symbol();
    StockType type();
    BigDecimal price();
    int quantity();
    BigDecimal marketValue();
}