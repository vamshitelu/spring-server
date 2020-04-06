package com.serverapp.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.serverapp.model.User;
import com.serverapp.service.IUserService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class UserController{

	@Autowired
	private IUserService userService;
		
	@PostMapping(value = "/signup")
	public ResponseEntity<User> saveUser(@RequestBody User user) {
		
		User userDTO =  userService.saveUser(user);	
		return new ResponseEntity<User>(userDTO, HttpStatus.OK );
	}		
}
