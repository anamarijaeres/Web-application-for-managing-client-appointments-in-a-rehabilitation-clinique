package opp.flow.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import opp.flow.CustomUserDetails;
import opp.flow.domain.Korisnik;
import opp.flow.repository.*;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;


@Service
public class CustomUserDetailsService implements UserDetailsService{

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Korisnik user=userRepository.findByusername(username);
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
	    String password = request.getParameter("password");
	    if(user==null) throw new UsernameNotFoundException("Username not found");
	    if(!user.getLozinka().equals(password)) {
	    	throw new UsernameNotFoundException("Username not found");
	    }
		return new CustomUserDetails(user);
	}

}
