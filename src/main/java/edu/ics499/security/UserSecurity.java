package edu.ics499.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import edu.ics499.serviceImp.DashboardServiceImp;
import edu.ics499.serviceImp.UserServiceImp;
import edu.ics499.serviceImp.WidgetServiceImp;

@Component("userSecurity")
public class UserSecurity {
	@Autowired
	private UserServiceImp userService;
	
	@Autowired
	private DashboardServiceImp dashService;
	
	@Autowired 
	private WidgetServiceImp widgetService;
	
	public boolean hasUserId(Authentication authentication, Long userId) {
		String currentUserName;
		if(authentication.getPrincipal() instanceof UserDetails) {
			currentUserName = ((UserDetails) authentication.getPrincipal()).getUsername();
		} else {
			currentUserName = authentication.getPrincipal().toString();
		}
		return userService.getById(userId).getUsername().equals(currentUserName);
	}
	
	public boolean doesDashboardBelongToUser(Authentication authentication, Long dashboardId) {
		
		//get user from dash service
		String currentUserName;
		if(authentication.getPrincipal() instanceof UserDetails) {
			currentUserName = ((UserDetails) authentication.getPrincipal()).getUsername();
		} else {
			currentUserName = authentication.getPrincipal().toString();
		}
		
		Long authUserId = userService.getByUsername(currentUserName).getUserID();
		Long dashOwnerId = dashService.getDashboardById(dashboardId).getUserId();
		
		return (authUserId == dashOwnerId);
	}
	
	public boolean doesWidgetBelongToUser(Authentication authentication, Long widgetId) {
		String currentUserName;
		if(authentication.getPrincipal() instanceof UserDetails) {
			currentUserName = ((UserDetails) authentication.getPrincipal()).getUsername();
		} else {
			currentUserName = authentication.getPrincipal().toString();
		}
		
		Long authUserId = userService.getByUsername(currentUserName).getUserID();
		Long widgetOwnerId = dashService.getDashboardById(widgetService.getWidgetById(widgetId).getDashboardId()).getUserId();
		
		return (authUserId == widgetOwnerId);
	}
}
