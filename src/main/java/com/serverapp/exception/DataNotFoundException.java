package com.serverapp.exception;

public class DataNotFoundException extends RuntimeException{

	private static final long serialVersionUID = 8612342383615414318L;

	public DataNotFoundException() {
	}
	
	public DataNotFoundException(String message) {
		super(message);
	}
	
	@Override
	public String getMessage() {
		return super.getMessage();
	}
	
}
