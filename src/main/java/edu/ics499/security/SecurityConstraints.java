package edu.ics499.security;

public class SecurityConstraints {
	public static final String SECRET = "SECRET_KEY";//change this after testing to a env variable, will have to drop tables and restart, also should be 32 chars long
	public static final long EXPIRATION_TIME = 3_600_000; //1 hr
	public static final long REFRESH_EXPIRATION_TIME = 3_600_000;//1 hr
	public static final String TOKEN_PREFIX = "Bearer ";
	public static final String HEADER_STRING = "Authorization";
	public static final String REFRESH_HEADER_STRING = "Refresh";
	public static final String SIGN_UP_URL = "/users/add";
}
