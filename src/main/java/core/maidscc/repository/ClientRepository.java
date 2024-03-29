package core.maidscc.repository;
import core.maidscc.entity.ClientManagement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<ClientManagement,Long> {


    Optional<ClientManagement> findByEmail(String email);
    ClientManagement findByAddressContaining(String address);

    long count();

    @Query("SELECT c.clientName, SUM(s.total) FROM ClientManagement c JOIN c.sales s GROUP BY c.clientName ORDER BY SUM(s.total) DESC")
    List<Object[]> findTopSpendingClients();

    @Query("SELECT c.clientName, COUNT(c) FROM ClientManagement c JOIN c.sales s GROUP BY c.clientName ORDER BY COUNT(c) DESC")
    List<Object[]> findClientActivity();

}
