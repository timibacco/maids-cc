package core.maidscc.repository;

import core.maidscc.entity.Sales;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface SalesRepository extends JpaRepository<Sales,Long> {
    int countByCreationDateBetween(LocalDate startDate, LocalDate endDate);

    Sales[] findByCreationDateBetween(LocalDate startDate, LocalDate endDate);

    @Query("SELECT SUM(s.total) FROM Sales s WHERE s.creationDate BETWEEN :startDate AND :endDate")
    Double getTotalRevenueBetweenDates(LocalDate startDate, LocalDate endDate);



    @Query("SELECT s.seller, COUNT(s) FROM Sales s WHERE s.creationDate BETWEEN :start_date AND :end_date GROUP BY s.seller ORDER BY COUNT(s) DESC")
    List<Object[]> findTopSellers(LocalDate start_date, LocalDate end_date);

    @Query("SELECT p.productName, COUNT(p) FROM Sales s JOIN s.products p GROUP BY p.productName ORDER BY COUNT(p) DESC")
    List<Object[]> findTopSellingProducts();


    }







