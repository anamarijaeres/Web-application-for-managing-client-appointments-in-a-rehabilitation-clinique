package opp.flow;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import opp.flow.domain.Korisnik;

import org.springframework.security.core.userdetails.UserDetails;

public class CustomUserDetails implements UserDetails{
	private static final long serialVersionUID = 1L;
	private Korisnik user;
	
	public CustomUserDetails(Korisnik user) {
		this.user=user;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<SimpleGrantedAuthority> list=new ArrayList<SimpleGrantedAuthority>();
		list.add(new SimpleGrantedAuthority("ROLE_CLIENT"));
		return list;
	}

	@Override
	public String getPassword() {
		return user.getLozinka();
	}

	@Override
	public String getUsername() {
		return user.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
	
}
