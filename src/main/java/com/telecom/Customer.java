package com.telecom;

import java.util.List;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class Customer {
	
	
	@Id
    @GeneratedValue
	private Long id;
	private String firstName;
	private String lastName;
	private List<Phone> phoneNumbers;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public List<Phone> getPhoneNumbers() {
		return phoneNumbers;
	}
	public void setPhoneNumbers(List<Phone> phoneNumbers) {
		this.phoneNumbers = phoneNumbers;
	}
}
