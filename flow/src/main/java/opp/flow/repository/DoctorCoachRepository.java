package opp.flow.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import opp.flow.model.DoctorCoach;

@Repository
public interface DoctorCoachRepository extends JpaRepository<DoctorCoach, Long> {
	DoctorCoach findByusername(String username);
}
