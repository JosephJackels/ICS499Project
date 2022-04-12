package edu.ics499.security;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class TokenRefreshException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	public TokenRefreshException(String token, String message) {
		super(String.format("Token: [%s] failed, %s", token, message));
	}
}