package opp.flow.repository;

import opp.flow.model.ClientCoach;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientCoachRepository extends JpaRepository<ClientCoach,Long> {
}
