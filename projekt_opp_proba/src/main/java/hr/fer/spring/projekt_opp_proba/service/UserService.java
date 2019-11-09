package hr.fer.spring.projekt_opp_proba.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hr.fer.spring.projekt_opp_proba.model.Role;
import hr.fer.spring.projekt_opp_proba.model.User;
import hr.fer.spring.projekt_opp_proba.ropository.RoleRepository;
import hr.fer.spring.projekt_opp_proba.ropository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	public User getUser(String userName) {
		return userRepository.findById(userName).get();
	}

	public void addUser(User userToBeAdded) {
		userRepository.save(userToBeAdded);
	}
}
