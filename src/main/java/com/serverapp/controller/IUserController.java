package com.serverapp.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.serverapp.model.User;

@RequestMapping("/api/v1")
public interface IUserController {

	@PostMapping(value="/saveUser")
	public ResponseEntity<User> saveUser(@RequestBody User user);
}
