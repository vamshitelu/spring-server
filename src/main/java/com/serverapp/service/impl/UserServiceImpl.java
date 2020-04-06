package com.serverapp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.serverapp.entity.UserEntity;
import com.serverapp.exception.UnAuthorizedException;
import com.serverapp.model.CustomUserDetails;
import com.serverapp.model.User;
import com.serverapp.repository.IUserRepository;
import com.serverapp.security.JwtTokenGenerator;
import com.serverapp.service.IUserService;

@Service
public class UserServiceImpl implements IUserService{

	@Autowired
	private IUserRepository userRepository;
	
	@Autowired
	private JwtTokenGenerator jwtTokenGenerator;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private ResourceBundleMessageSource message;
	
	@Override
	public User saveUser(User user) {
	
		UserEntity userEntity = new UserEntity();
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user.setRole("user");
		user.setActive(1);
		map(user, userEntity);
		UserEntity outEntity = userRepository.save(userEntity);
		map(outEntity, user);
		user.setPassword(null);
		return user;
	}

	@Override
	public User findByUsername(String userName) {
		
		UserEntity userEntity = userRepository.findByUserName(userName);
		
		/*
		 * if(userEntity == null) {
		 * 
		 * throw new DataNotFoundException( message.getMessage("no.data",new Object[]
		 * {"User object"}, LocaleContextHolder.getLocale())); }
		 */
		
		User user = new User();
		if(userEntity != null)
			user.setUserName(userEntity.getUserName());
		
		return (User) user;
	}

	@Override
	public CustomUserDetails loginUser(User user) {
		
		CustomUserDetails customUser = null;
		
		if(user.getUserName() == null || user.getUserName().equals("")) {
			
			throw new UnAuthorizedException(
					message.getMessage("user.empty",null, LocaleContextHolder.getLocale()));
		}
		
		if(user.getPassword() == null || user.getPassword().equals("")) {
			
			throw new UnAuthorizedException(
					message.getMessage("password.empty",null, LocaleContextHolder.getLocale()));
		}
		
		UserEntity userEntity = userRepository.findByUserName(user.getUserName());
		
		if(userEntity == null || !passwordEncoder.matches(user.getPassword(), userEntity.getPassword()))
			
			throw new UnAuthorizedException(
					message.getMessage("invalid.user",null, LocaleContextHolder.getLocale()));
		else {
			
			User newUser = new User();
			newUser.setId(userEntity.getId());
			newUser.setUserName(userEntity.getUserName());
			newUser.setRole(userEntity.getRole());
			
			String token = jwtTokenGenerator.generate(newUser);
			
			customUser = new CustomUserDetails(
					userEntity.getUserName(), 
					userEntity.getId(), 
					token, AuthorityUtils.createAuthorityList((String)userEntity.getRole()));
			customUser.setFirstName(userEntity.getFirstName());
			customUser.setLastName(userEntity.getLastName());
		}
		
		return customUser;
	}

}
