package edu.ics499.security2;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTResponse;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;

import edu.ics499.model.User;
//import edu.ics499.security2.SecurityConstraints;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	
	private AuthenticationManager authenticationManager;
	
	public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
		//default ednpoint, can be changed
		setFilterProcessesUrl("/login");
	}
	
	@Override
	public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res) throws AuthenticationException {
		try {
			User creds = new ObjectMapper().readValue(req.getInputStream(), User.class);
			
			return authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(
							creds.getUsername(),
							creds.getPassword(),
							new ArrayList<>()
					)
			);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	@Override
	protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse res, FilterChain chain, Authentication auth) throws IOException {
		String token = JWT.create()
				.withSubject(((UserPrincipal) auth.getPrincipal()).getUsername())
				.withExpiresAt(new Date(System.currentTimeMillis() + SecurityConstraints.EXPIRATION_TIME))
				.sign(Algorithm.HMAC512(SecurityConstraints.SECRET.getBytes()));
		String refreshToken = JWT.create()
				.withSubject(((UserPrincipal) auth.getPrincipal()).getUsername())
				.withExpiresAt(new Date(System.currentTimeMillis() + SecurityConstraints.REFRESH_EXPIRATION_TIME))
				.sign(Algorithm.HMAC512(SecurityConstraints.SECRET.getBytes()));
		//String body = ((UserPrincipal) auth.getPrincipal()).getUsername() + " " + token;
		//String body = "{ \"token\": " + "\"" + token + "\"}";
		//auth.g
		String body = new JWTResponse(token, refreshToken, ((UserPrincipal) auth.getPrincipal()).getUsername()).toJson();
		res.getWriter().write(body);
		res.getWriter().flush();
	}
	
	@Override
	protected void unsuccessfulAuthentication(HttpServletRequest req, HttpServletResponse res, AuthenticationException authException) throws IOException {
		System.out.println("unsuccess");
	}
}
