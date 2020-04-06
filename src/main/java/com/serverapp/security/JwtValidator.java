package com.serverapp.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Component;

import com.serverapp.model.CustomUserDetails;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

@Component
public class JwtValidator {

	@Value("${security.sceret}")
	private String secret;
	
	public CustomUserDetails validate(String token) {
		
		CustomUserDetails user = null;
		
		try {
			
			Claims claims = Jwts.parser()
				.setSigningKey(secret)
				.parseClaimsJws(token)
				.getBody();
			
			user = new CustomUserDetails(claims.getSubject(),
					Long.parseLong((String) claims.get("id")),
					token,
					AuthorityUtils.createAuthorityList((String)claims.get("role")));
					
		}catch(Exception e) {
			System.out.println(e);
		}
		
		return user;
	}
}
