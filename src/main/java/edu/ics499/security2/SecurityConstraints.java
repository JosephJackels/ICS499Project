package edu.ics499.security2;

public class SecurityConstraints {
	public static final String SECRET = "SECRET_KEY";//change this after testing to a env variable, will have to drop tables and restart, also should be 32 chars long
	public static final long EXPIRATION_TIME = 60_000; //1 minutes
	public static final long REFRESH_EXPIRATION_TIME = 900_000;//2min
	public static final String TOKEN_PREFIX = "Bearer ";
	public static final String HEADER_STRING = "Authorization";
	public static final String REFRESH_HEADER_STRING = "Refresh";
	public static final String SIGN_UP_URL = "/users/add";
}
