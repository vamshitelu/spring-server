package com.serverapp.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.serverapp.model.CustomUserDetails;
import com.serverapp.model.User;
import com.serverapp.service.IUserService;

@CrossOrigin(origins = "http://localhost:9500")
@RestController
public class AuthenticationController {

	@Autowired
	private IUserService userService;
	
	@PostMapping(value="/authenticate")
	public ResponseEntity<CustomUserDetails> loginUser(@RequestBody User user) {
		
		CustomUserDetails customUser = userService.loginUser(user);
		
		return new ResponseEntity<>(customUser, HttpStatus.OK);
	}
	
	@GetMapping("/find")
	public ResponseEntity<User> verifyUsername(@RequestParam String name){
		
		User userDTO = userService.findByUsername(name);
		
		return new ResponseEntity<>(userDTO, HttpStatus.OK);
	}
}
