package edu.ics499.serviceImp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import edu.ics499.model.User;
import edu.ics499.repositories.UserRepository;
import edu.ics499.security2.UserPrincipal;
import edu.ics499.service.*;

@Service
public class UserServiceImp implements UserService, UserDetailsService {
	@Autowired
	private UserRepository userRepo;	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
    @Override
    public User addNewUser(User user) {
    	user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		return userRepo.saveAndFlush(user);
    }

	@Override
	public List<User> findAll() {
		return userRepo.findAll();
	}

	@Override
	public User getById(Long id) {
		return userRepo.findById(id).orElseThrow(() -> new RuntimeException());
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepo.findByUsername(username);
		if(user == null) {
			throw new UsernameNotFoundException(username);
		}
		return new UserPrincipal(user);
	}
    
}
