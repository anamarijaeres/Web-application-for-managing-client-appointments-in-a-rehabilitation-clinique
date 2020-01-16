package opp.flow.repository;

import opp.flow.model.Training;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.text.DateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Repository
public interface TrainingRepository extends JpaRepository<Training, Long> {
    List<Training> findByUsernameAndDateAndDone (String username, LocalDate date, boolean done);

    List<Training> findByUsernameAndDone(String username, boolean done);

    List<Training> findByUsernameAndDate(String username, LocalDate date);
}
