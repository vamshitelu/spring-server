package com.serverapp.model;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomUserDetails implements UserDetails{
	
	private static final long serialVersionUID = 5627541732160702628L;

	private String userName;
	private String firstName;
	private String lastName;
	private String token;
	private Long id;
	private Collection<? extends GrantedAuthority> authorities;
	
	public CustomUserDetails(String userName,Long id, String token, List<GrantedAuthority> grantedAuthorities) {
		this.userName = userName;
		this.id = id;
		this.token = token;
		this.authorities = grantedAuthorities;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@JsonIgnore( value = false)
	@Override
	public String getPassword() {
		return null;	
	}
	
	@Override
	public String getUsername() {
		return userName;
	}
	
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
	
}
