package com.serverapp.security;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.serverapp.model.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenGenerator {

	@Value("${security.sceret}")
	private String secret;
	
	public String generate(User user) {
		
		Claims claims = Jwts.claims()
				.setSubject(user.getUserName());
		claims.put("id", String.valueOf(user.getId()));
		claims.put("role", user.getRole());
		
		return Jwts.builder()
			.setClaims(claims)
			.signWith(SignatureAlgorithm.HS512, secret)
			.setExpiration(new Date(System.currentTimeMillis()+864000000))
			.compact();
	}
}
