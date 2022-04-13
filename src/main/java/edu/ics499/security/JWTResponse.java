package edu.ics499.security;

public class JWTResponse {
	private String token;
	private String type = "Bearer";
	private String refreshToken;
	private String username;
	private long userId;
	
	public JWTResponse(String token, String refreshToken, String username, long userId) {
		this.token = token;
		this.refreshToken = refreshToken;
		this.username = username;
		this.userId = userId;
	}
	
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getRefreshToken() {
		return refreshToken;
	}
	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	@Override
	public String toString() {
		return "JWTResponse [token=" + token + ", type=" + type + ", refreshToken=" + refreshToken + ", username="
				+ username + "]";
	}
	public String toJson() {
		return "{\"token\": \"" + token + "\", \"type\": \"" + type + "\", \"refreshToken\": \"" + refreshToken + "\", \"username\": \"" + username + "\", \"userId\": \"" + userId + "\"}";
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}
}
