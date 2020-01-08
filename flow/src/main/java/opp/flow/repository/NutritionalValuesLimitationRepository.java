package opp.flow.repository;

import opp.flow.model.CategoryLimitation;
import opp.flow.model.NutritionalValuesLimitation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NutritionalValuesLimitationRepository extends JpaRepository<NutritionalValuesLimitation, Long> {
}
