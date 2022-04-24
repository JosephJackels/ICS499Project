package edu.ics499.security;
/**
 * COnstraints used for various security processes
 * 	Secret - the key used for generating tokens
 * 	Expiration Time - amount of time before tokens expire
 * 	Refresh Expiration Tme - amount of time before refresh tokens expire
 * 	Token PRefix - expected prefix for JWT tokens used
 * 	Header String - Key used in Header's when making requests with bearer tokens
 * 	Refresh Header String - Key used in header when making requesnts wiht refresh token
 * 	Sign Up Url - url used for creating users that does not require authorization/authentication 
 * @author joe
 *
 */
public class SecurityConstraints {
	public static final String SECRET = "SECRET_KEY";//change this after testing to a env variable, will have to drop tables and restart, also should be 32 chars long
	public static final long EXPIRATION_TIME = 3_600_000; //1 hr
	public static final long REFRESH_EXPIRATION_TIME = 3_600_000;//1 hr
	public static final String TOKEN_PREFIX = "Bearer ";
	public static final String HEADER_STRING = "Authorization";
	public static final String REFRESH_HEADER_STRING = "Refresh";
	public static final String SIGN_UP_URL = "/users/add";
}
