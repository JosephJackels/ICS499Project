package edu.ics499.security2;

public class SecurityConstraints {
	public static final String SECRET = "SECRET_KEY";//change this after testing to a env variable, will have to drop tables and restart
	public static final long EXPIRATION_TIME = 1_800_000; //15 minutes
	public static final String TOKEN_PREFIX = "Bearer ";
	public static final String HEADER_STRING = "Authorization";
	public static final String SIGN_UP_URL = "/users/add";
}
