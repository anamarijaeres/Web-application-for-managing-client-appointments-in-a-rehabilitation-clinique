package hr.fer.spring.projekt_opp_proba.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import hr.fer.spring.projekt_opp_proba.model.User;
import hr.fer.spring.projekt_opp_proba.service.UserService;

@RestController
public class UserController {

	@Autowired
	private UserService userService;
	
	@GetMapping("/home/{userName}")
	public User getUser(@PathVariable("userName") String userName) {
		return userService.getUser(userName);
	}
	
	@PostMapping("/register/newuser")
	public void addUser(@RequestBody User userToBeAdded) {
		userService.addUser(userToBeAdded);
	}
}
