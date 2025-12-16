package portfolio.model;

import java.math.BigDecimal;
import java.util.Map;

public record PortfolioSummary(
        BigDecimal totalValue,
        Map<StockType, BigDecimal> totalValueByType,
        int totalHoldings
) {}
