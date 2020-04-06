package com.serverapp.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class User{
	
	private Long id;
	
	private String firstName;
	
	private String lastName;
	
	private String userName;
	
	private String password;
	
	private String email;
	
	private String mobile;

	private int active;
	
	private String role;

	public User() {
	}

	public User(User user) {
		this.id = user.getId();
		this.firstName = user.getFirstName();
		this.lastName = user.getLastName();
		this.userName = user.getUserName();
		this.password = user.getPassword();
		this.email = user.getEmail();
		this.mobile = user.getMobile();
		this.active = user.getActive();
		this.role = user.getRole();
	}
	
	
	
	
}
