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
	
	@Autowired
	private DashboardServiceImp dashService;
	
	public boolean hasUserId(Authentication authentication, Long userId) {
		String currentUserName;
		if(authentication.getPrincipal() instanceof UserDetails) {
			currentUserName = ((UserDetails) authentication.getPrincipal()).getUsername();
		} else {
			currentUserName = authentication.getPrincipal().toString();
		}
		return userService.getById(userId).getUsername().equals(currentUserName);
	}
	
	public boolean doesDashboardBelongToUser(Authentication auth, Long dashboardId) {
		
		//get user from dash service
		//check that its the same as user in auth
	}
}
