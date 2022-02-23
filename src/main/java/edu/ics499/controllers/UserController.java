package edu.ics499.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.ics499.serviceImp.DashboardServiceImp;
import edu.ics499.serviceImp.UserServiceImp;
import edu.ics499.serviceImp.WidgetServiceImp;
import edu.ics499.model.Dashboard;
import edu.ics499.model.User;

@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired 
	private UserServiceImp userService;
	@Autowired 
	private DashboardServiceImp dashboardService;
	
	//get request to localhost:port/users/all
	@GetMapping("/all")
	public List<User> list(){
		return userService.findAll();
		//return userRepo.findAll();
	}
	
	//get request to localhost:port/users/one/{id} -- replace {id} with userID ex users/one/1
	@GetMapping("/one/{id}")
	public User getUserById(@PathVariable Long id) {
		//maybe make a custom exception that extends runtime exception?
		return userService.getById(id);
		//return userRepo.findById(id).orElseThrow(() -> new RuntimeException());
	}
	
	//get request to localhost:port/users/one/{id}/dashboard -- replace {id} with userID ex users/one/1/dashboard
	@GetMapping("/one/{id}/dashboard")
	public Dashboard getDashboardForUser(@PathVariable Long id) {
		return dashboardService.getDashboardByUserId(id);
	}
	
	//post request to localhost:port/users/add with a User object
	//in the request body? ex. {"username":"joe", "password":"pass123"}
	@PostMapping("/add")
	public User create(@RequestBody final User user) {
		//user.setDashboard(new Dashboard());
		//should be moved to as ervice I think
		return userService.addNewUser(user);
	}
}
