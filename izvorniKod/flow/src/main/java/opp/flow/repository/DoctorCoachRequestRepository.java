package opp.flow.repository;

import opp.flow.model.DoctorCoachRequests;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorCoachRequestRepository extends JpaRepository<DoctorCoachRequests,Long> {

}
