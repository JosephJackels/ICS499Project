package edu.ics499.security;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import edu.ics499.serviceImp.UserServiceImp;

@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {
	
	private UserServiceImp userService;
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	public WebSecurity(UserServiceImp userService, BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.userService = userService;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.csrf().disable()
			.cors().and().authorizeRequests()
			.antMatchers(HttpMethod.POST, SecurityConstraints.SIGN_UP_URL).permitAll() //everyone can access create user page
			.antMatchers("/users/one/{id}", "/users/one/{id}/dashboard") //must be logged in for pages specific to user and be that user
				.access("@userSecurity.hasUserId(authentication, #id)")
				//.permitAll()
			.antMatchers("/dashboards/remove/{dashId}", "dashboards/one/{dashId}", "/dashboards/add/{dashId}") // dashboard must belong to logged in user
				.access("@userSecurity.doesDashboardBelongToUser(authentication, #dashId)")
			.antMatchers("widgets/get/{id}", "widgets/get/{id}/payload", "widgets/update/{id}")// widget must belong to user
				.access("@userSecurity.doesWidgetBelongToUser(authentication, #id)")
			.anyRequest().authenticated() //catch all for rest - just must be logged in and authenticated. anyone can do get request to all users, all widgets etc.
			.and()
			.addFilter(new JWTAuthenticationFilter(authenticationManager()))
			.addFilter(new JWTAuthorizationFilter(authenticationManager()))
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}
	
	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userService).passwordEncoder(bCryptPasswordEncoder);
	}
	
	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		
		CorsConfiguration corsConfiguration = new CorsConfiguration().applyPermitDefaultValues();
		source.registerCorsConfiguration("/**", corsConfiguration);
		
		return source;
	}
}
