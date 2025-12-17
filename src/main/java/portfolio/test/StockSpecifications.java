package portfolio.test;

public class StockSpecifications {

    public static Specification<StockEntity> withCriteria(
            StockType type,
            BigDecimal minPrice,
            String prefix
    ) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (type != null)
                predicates.add(cb.equal(root.get("type"), type));

            if (minPrice != null)
                predicates.add(cb.greaterThanOrEqualTo(
                        root.get("price"), minPrice));

            if (prefix != null && !prefix.isBlank())
                predicates.add(cb.like(
                        cb.lower(root.get("symbol")),
                        prefix.toLowerCase() + "%"));

            return cb.and(predicates.toArray(Predicate[]::new));
        };
    }
}
