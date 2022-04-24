package edu.ics499.service;

import java.util.List;

import edu.ics499.model.User;
/**
 * Service class for User manipulation
 *
 */
public interface UserService {
    //boolean isValid(String username, String password);
	
	User addNewUser(User user);
	List<User> findAll();
	User getById(Long id);
	User getByUsername(String username);
}
