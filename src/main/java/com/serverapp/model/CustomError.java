package com.serverapp.model;

import lombok.Data;

@Data
public class CustomError {
	
	private String message;
	
	private Class clazz;
	
	public CustomError() {
	}

	public CustomError(String message, Class clazz) {
		this.message = message;
		this.clazz = clazz;
	}
	
	
}
