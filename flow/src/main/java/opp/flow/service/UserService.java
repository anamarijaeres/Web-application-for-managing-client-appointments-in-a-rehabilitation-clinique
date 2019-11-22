package opp.flow.service;

import java.util.List;

import opp.flow.domain.Korisnik;
import opp.flow.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service
public class UserService{

    @Autowired
    private UserRepository repository;

    public void createUser(Korisnik korisnik) {
    	repository.save(korisnik);
    }

	public Korisnik loadUser(String username, String password) {
		Korisnik user=repository.findByusername(username);
		if(user==null) return null;
		if(!user.getLozinka().equals(password)) {
			return null;
		}
		return user;
	}
}
