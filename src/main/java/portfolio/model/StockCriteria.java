package portfolio.model;

import java.math.BigDecimal;
import java.util.Optional;

public class StockCriteria {
    private final StockType type;          // nullable = any
    private final BigDecimal minValue;     // nullable = any
    private final String symbolPrefix;     // null/blank = any

    public StockCriteria(StockType type, BigDecimal minValue, String symbolPrefix) {
        this.type = type;
        this.minValue = minValue;
        this.symbolPrefix = symbolPrefix;
    }

    public Optional<StockType> type() { return Optional.ofNullable(type); }
    public Optional<BigDecimal> minValue() { return Optional.ofNullable(minValue); }
    public Optional<String> symbolPrefix() {
        return (symbolPrefix == null || symbolPrefix.isBlank())
                ? Optional.empty()
                : Optional.of(symbolPrefix);
    }
}
