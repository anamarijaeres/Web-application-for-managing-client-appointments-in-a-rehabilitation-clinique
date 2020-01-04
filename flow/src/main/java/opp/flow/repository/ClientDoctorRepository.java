package opp.flow.repository;

import opp.flow.model.ClientDoctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientDoctorRepository extends JpaRepository<ClientDoctor, Long> {
}
