package edu.ics499.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import edu.ics499.repositories.UserRepository;
import edu.ics499.model.User;

@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private UserRepository userRepo;
	
	//get request to localhost:port/users/all
	@GetMapping("/all")
	public List<User> list(){
		System.out.println("all");
		return userRepo.findAll();
	}
	
	//get request to localhost:port/users/one?id=#
	@GetMapping("/one")
	public User get(@RequestParam Long id) {
		//maybe make a custom exception that extends runtime exception?
		return userRepo.findById(id).orElseThrow(() -> new RuntimeException());
	}
	
	//post request to localhost:port/users/add with a User object
	//in the request body? not sure how this works yet/how to use it
	@PostMapping("/add")
	public User create(@RequestBody final User user) {

		return userRepo.saveAndFlush(user);
		
	}
}
