package core.maidscc.repository;

import core.maidscc.entity.ProductManagement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<ProductManagement,Long> {

//        List<Object[]> findByStatus();

    }

