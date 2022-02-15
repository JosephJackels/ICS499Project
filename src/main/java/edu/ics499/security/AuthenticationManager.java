package edu.ics499.security;

/**
 * Working from https://spring.io/guides/topicals/spring-security-architecture/
 */

public interface AuthenticationManager {

    /*
     * Return an Authentication (normally with authenticated=true) if it can verify that the input
     * represents a valid principal.
     *
     * Throw an AuthenticationException if it believes that the input represents an invalid
     * principal.
     *
     * Return null if it cannot decide.
     */
    Authentication authenticate(Authentication authentication) throws AuthenticationException;
}
