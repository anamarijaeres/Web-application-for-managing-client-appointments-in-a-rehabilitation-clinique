package opp.flow.repository;

import opp.flow.model.Diet;
import opp.flow.model.Product;
import opp.flow.model.ProductLimitation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductLimitationRepository extends JpaRepository<ProductLimitation, Long> {
}
