package com.telecom.error;

public class PhoneNotFoundException extends RuntimeException {
	
	public PhoneNotFoundException(Long id) {
		super("Phone not found : "+id);
	}
}
