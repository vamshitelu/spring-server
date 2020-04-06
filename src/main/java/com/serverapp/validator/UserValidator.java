package com.serverapp.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.serverapp.model.User;
import com.serverapp.service.IUserService;

@Component
public class UserValidator implements Validator{

	@Autowired
	private IUserService userService;
	
	@Override
	public boolean supports(Class<?> clazz) {
		
		return User.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		User user = (User) target;
		
		ValidationUtils.rejectIfEmpty(errors, "userName", "user.empty");
		
		if(user.getUserName().length() < 6 || user.getUserName().length() > 32) {
			errors.rejectValue("userName", "size.userName");
		}
		
		if(userService.findByUsername(user.getUserName())!= null) {
			errors.rejectValue("userName", "duplication.userName");
		}
		
	}

	

	
}
