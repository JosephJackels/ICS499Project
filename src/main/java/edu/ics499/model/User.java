package edu.ics499.model;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.lang.NonNull;

/**
 * User Entity
 * - holds user information - username & hashed password
 * - contains a dashboard of user's widgets
 */

@Entity
@Table(name="users")
public class User {
	
	@Id
	@GeneratedValue
	private Long userID;
	
	@Column(unique=true)
	@NonNull
	private String username;
	
	private String password;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Dashboard dashboard;
	
	//default Constructor
	public User() {
		super();
		this.dashboard = new Dashboard();
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
