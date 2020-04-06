package com.serverapp.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.serverapp.model.CustomUserDetails;

@Component
public class JwtAuthenticationProvider 
	extends AbstractUserDetailsAuthenticationProvider{

	@Autowired
	private JwtValidator validator;
	
	@Override
	protected void additionalAuthenticationChecks(UserDetails userDetails,
			UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
	}

	@Override
	protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken authentication)
			throws AuthenticationException {
		
		JwtAuthenticationToken authenticationToken = (JwtAuthenticationToken) authentication;
		
		String token = authenticationToken.getToken();
		
		CustomUserDetails user = validator.validate(token);
		
		if(user == null) {
			throw new RuntimeException("Jwt Token is incorrect");
		}
		
		/*
		 * List<GrantedAuthority> grantedAuthorities =
		 * AuthorityUtils.commaSeparatedStringToAuthorityList(user.getAuthorities());
		 * 
		 * return new CustomUserDetails( user.getUserName(), user.getId(),token,
		 * grantedAuthorities);
		 */
		return user;
	}
	
	@Override
	public boolean supports(Class<?> authentication) {
		return JwtAuthenticationToken.class.isAssignableFrom(authentication);
	}

}
