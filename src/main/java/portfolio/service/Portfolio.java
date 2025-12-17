package portfolio.service;


import portfolio.model.PortfolioSummary;
import portfolio.model.StockCriteria;
import portfolio.model.StockType;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

public class Portfolio {

    private final List<Stock> stocks = new ArrayList<>();

    public void add(Stock stock) {
        if (stock == null) throw new IllegalArgumentException("stock cannot be null");
        stocks.add(stock);
    }

    public List<Stock> stocks() {
        return Collections.unmodifiableList(stocks);
    }

    public BigDecimal totalMarketValue() {
        return stocks.stream()
                .map(Stock::marketValue)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }


    public List<Stock> findByType(StockType  type){

        if(type ==null) throw new IllegalArgumentException("type cannot be null");

        return stocks.stream()
                .filter(s -> s.type() == type)
                .collect(Collectors.toUnmodifiableList());
    }


    public List<Stock> topNList(int limit){

       return stocks.stream()
               .sorted(Comparator.comparing(Stock::marketValue).reversed())
               .limit(limit)
               .collect(Collectors.toUnmodifiableList());



    }

    public List<Stock> find(StockCriteria criteria) {
        if (criteria == null) {
            throw new IllegalArgumentException("criteria cannot be null");
        }

        return stocks.stream()
                .filter(s -> criteria.type().map(t -> s.type() == t).orElse(true))
                .filter(s -> criteria.minValue()
                        .map(min -> s.marketValue().compareTo(min) >= 0)
                        .orElse(true))
                .filter(s -> criteria.symbolPrefix()
                        .map(prefix -> s.symbol().startsWith(prefix))
                        .orElse(true))
                .collect(Collectors.toUnmodifiableList());
    }


    public PortfolioSummary summary() {
        BigDecimal total = totalMarketValue();

        Map<StockType, BigDecimal> byType = stocks.stream()
                .collect(Collectors.groupingBy(
                        Stock::type,
                        Collectors.mapping(Stock::marketValue,
                                Collectors.reducing(BigDecimal.ZERO, BigDecimal::add))
                ));

        return new PortfolioSummary(total, byType, stocks.size());
    }


}
