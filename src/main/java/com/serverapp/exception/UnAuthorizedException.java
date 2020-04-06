package com.serverapp.exception;

public class UnAuthorizedException extends RuntimeException{

	private static final long serialVersionUID = -9186282634390438713L;

	public UnAuthorizedException() {
	}
	
	public UnAuthorizedException(String message){
		super(message);
	}
	
	@Override
	public String getMessage() {
		return super.getMessage();
	}
}
