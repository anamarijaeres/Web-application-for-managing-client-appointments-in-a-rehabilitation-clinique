package hr.fer.spring.projekt_opp_proba.ropository;

import org.springframework.data.jpa.repository.JpaRepository;

import hr.fer.spring.projekt_opp_proba.model.User;

public interface UserRepository extends JpaRepository<User, String>{

}
