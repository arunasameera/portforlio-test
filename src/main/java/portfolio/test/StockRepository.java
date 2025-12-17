package portfolio.test;

public class StockRepository {


    public interface StockRepository extends JpaRepository<StockEntity, Long> {

        @Query("""
        SELECT s
        FROM StockEntity s
        WHERE s.type = :type
          AND s.price >= :minPrice
    """)
        List<StockEntity> findByTypeAndMinPrice(
                @Param("type") StockType type,
                @Param("minPrice") BigDecimal minPrice
        );
    }



    @Query("""
    SELECT s
    FROM StockEntity s
    WHERE (:type IS NULL OR s.type = :type)
      AND (:prefix IS NULL OR LOWER(s.symbol) LIKE LOWER(CONCAT(:prefix, '%')))
""")
    List<StockEntity> search(
            @Param("type") StockType type,
            @Param("prefix") String symbolPrefix
    );



    @Query("""
    SELECT s.type, SUM(s.price * s.quantity)
    FROM StockEntity s
    GROUP BY s.type
""")
    List<Object[]> totalValueByType();



    repo.findAll(StockSpecifications.withCriteria(type, minPrice, prefix));



}
