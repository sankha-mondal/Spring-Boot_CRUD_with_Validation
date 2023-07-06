package com.entity;

import javax.persistence.*;
import javax.validation.constraints.*;


@Entity
@Table(name = "users")
public class User {
	
	@Id
	// email should be a valid email format
	// email should not be null or empty
	@NotEmpty(message = "Please enter a valid email Id")
	@Email(message = "Please enter a valid email Id", regexp="^[a-zA-Z0-9._-]+@[a-zA-Z0-9-]+\\.[a-zA-Z.]{2,5}")
	private String email;
	
	
	@Column(name = "name", nullable = false)
	// user name should not be null or empty
	// user name should have at least 2 characters
	@NotEmpty(message = "Please enter name")
	@Size(min = 2, message = "user name should have at least 2 characters")
	private String name;
	
	
	// password should not be null or empty
	// password should have at least 8 characters
	@NotEmpty(message = "Please enter password")
	@Size(min = 8, message = "password should have at least 8 characters")
	@Pattern(message = "Password should have atleast 8 character, 1 uppercase letter, 1 lowercase letter and 1 number", regexp="^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[!@#$%^&*()_+-/]).{8,}$")
	private String password;

	/*
	    {
    		"name": "Sankha",
    		"email": "ssm@gmail.com",
    		"password": "Sankha@123"
		}
	 */
	
	
	public User() {
		
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "User [email=" + email + ", name=" + name + ", password=" + password + "]";
	}

	public User(
			@NotEmpty(message = "Please enter a valid email Id") @Email(message = "Please enter a valid email Id", regexp = "^[a-zA-Z0-9._-]+@[a-zA-Z0-9-]+\\.[a-zA-Z.]{2,5}") String email,
			@NotEmpty(message = "Please enter name") @Size(min = 2, message = "user name should have at least 2 characters") String name,
			@NotEmpty(message = "Please enter password") @Size(min = 8, message = "password should have at least 8 characters") String password) {
		super();
		this.email = email;
		this.name = name;
		this.password = password;
	}
	
	
}
