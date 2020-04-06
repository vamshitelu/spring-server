package com.serverapp.service;

import com.serverapp.model.CustomUserDetails;
import com.serverapp.model.User;
import com.serverapp.util.IService;

public interface IUserService extends IService{

	public User saveUser(User user);
	
	public User findByUsername(String userName);
	
	public CustomUserDetails loginUser(User user);
}
