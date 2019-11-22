package opp.flow.repository;


import opp.flow.domain.Korisnik;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Korisnik, Long> {
	Korisnik findByusername(String username);
}
