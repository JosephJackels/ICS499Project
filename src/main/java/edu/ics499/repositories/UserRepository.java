package edu.ics499.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.ics499.model.User;
/**
 * User Repository used for performing operations in DB involving users
 *
 */
public interface UserRepository extends JpaRepository<User, Long> {
	User findByUsername(String username);
}
