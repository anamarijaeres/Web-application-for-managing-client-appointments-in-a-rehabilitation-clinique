package opp.flow.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import opp.flow.model.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
	Client findByusername(String username);
}
