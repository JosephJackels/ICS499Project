package edu.ics499.security2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import edu.ics499.serviceImp.UserServiceImp;

@Component("userSecurity")
public class UserSecurity {
	@Autowired
	private UserServiceImp userService;
	
	public boolean hasUserId(Authentication authentication, Long userId) {
		String currentUserName;
		if(authentication.getPrincipal() instanceof UserDetails) {
			currentUserName = ((UserDetails) authentication.getPrincipal()).getUsername();
		} else {
			currentUserName = authentication.getPrincipal().toString();
		}
		return userService.getById(userId).getUsername().equals(currentUserName);
	}
}
