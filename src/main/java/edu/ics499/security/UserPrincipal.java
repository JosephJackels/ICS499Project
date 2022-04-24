package edu.ics499.security;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import edu.ics499.model.User;
/**
 * Class to be used to handle login information/token based authorization 
 *
 */
public class UserPrincipal implements UserDetails {
	
	private final User user;
	
	public UserPrincipal(User user) {
		this.user = user;
	}
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Collections.<GrantedAuthority>singletonList(new SimpleGrantedAuthority("User"));
	}

	@Override
	public String getPassword() {
		return user.getPassword();
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
	
	public User getUser() {
		return user;
	}
	
	public Long getId() {
		return user.getUserID();
	}
}
