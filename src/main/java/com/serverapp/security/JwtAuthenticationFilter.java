package com.serverapp.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.serverapp.model.CustomUserDetails;

import lombok.extern.slf4j.Slf4j;

@WebFilter(urlPatterns = "/")
@Component
@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter{
	
	@Autowired
	private JwtValidator validator;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String jwt = getJwtFromRequest(request);
		
		if(jwt != null) {
			CustomUserDetails user = validator.validate(jwt);
			if(user != null) {
				UsernamePasswordAuthenticationToken authenticationToken =
					new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
			
				authenticationToken.setDetails(
					new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(authenticationToken);
			}else {
				log.error("Erro fetching user details");
			}
		}else {
			log.error("JWT is not valid.");
		}
			
		filterChain.doFilter(request, response);
		
	}
	
	private String getJwtFromRequest(HttpServletRequest request) {
		
		String bearerToken = request.getHeader("Authorization");
		
		if(StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
			return bearerToken.substring(7, bearerToken.length());
		}
		log.debug("Invalid or empty JWT Token");
		return null;
	}
	
}
