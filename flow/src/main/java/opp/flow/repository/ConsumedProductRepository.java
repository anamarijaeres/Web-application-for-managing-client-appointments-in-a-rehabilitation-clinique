package opp.flow.repository;

import opp.flow.model.ConsumedProduct;
import opp.flow.model.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConsumedProductRepository extends JpaRepository<ConsumedProduct, Long> {
    List<ConsumedProduct> findByusername (String name);
}
