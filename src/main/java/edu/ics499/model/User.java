package edu.ics499.model;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;


//extend user principle/detail?
@Entity
@Table(name="users")
public class User {
	
	@Id
	@GeneratedValue
	private Long userID;
	
	private String username;
	
	//should this be different in order to be secure?
	//like a hashed/salted password is stored not the real password?
	//we can just use a String for now and figure that out when we get to that point
	private String password;
	
	//not sure what the cascade thing is but he has it in his?
	//something to lookup I guess
	@OneToOne(cascade = CascadeType.ALL)
	private Dashboard dashboard;
	
	//default Constructor
	public User() {
		super();
	}
	
	public User(String username, String password) {
		this.username = username;
		this.password = password;
		this.dashboard = new Dashboard();
	}
	
	public Long getUserID() {
		return userID;
	}

	public void setUserID(Long userID) {
		this.userID = userID;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Dashboard getDashboard() {
		return dashboard;
	}

	public void setDashboard(Dashboard dashboard) {
		this.dashboard = dashboard;
	}

	@Override
	public int hashCode() {
		return Objects.hash(password, userID, username);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(password, other.password) && Objects.equals(userID, other.userID)
				&& Objects.equals(username, other.username) && Objects.equals(dashboard, other.dashboard);
	}

	@Override
	public String toString() {
		return "User [userID=" + userID + ", username=" + username + ", password=" + password + ", dashboard="
				+ dashboard.toString() + "]";
	}
	
	
}
