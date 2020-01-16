package opp.flow.repository;

import opp.flow.model.TrainingStatistics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrainingStatisticsRepository extends JpaRepository<TrainingStatistics, Long> {

    public List<TrainingStatistics> findByUsername(String username);


}
