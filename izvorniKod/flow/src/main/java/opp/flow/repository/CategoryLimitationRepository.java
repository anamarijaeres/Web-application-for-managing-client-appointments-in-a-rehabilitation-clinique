package opp.flow.repository;

import opp.flow.model.CategoryLimitation;
import opp.flow.model.ProductLimitation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryLimitationRepository extends JpaRepository<CategoryLimitation, Long> {
    List<CategoryLimitation> findByusername(String username);
}
