package com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.entity.User;
import com.repository.UserRepository;

import java.io.IOException;
import java.util.Optional;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepo;
	
// ===========================================================================================================
	
	//  Insert Operation | By PK | By User return type:-
	
	public User storeUser_userType(User user) {
		boolean res = userRepo.existsById(user.getEmail());
		System.out.println(res);
		System.out.println(user.getEmail());
		
		if(res) 
			return null;
		else 
			return userRepo.save(user);
	}

// ==========================================================================================================

	//  Insert Operation | By PK | By String return type:-
		
	public String storeUser_stringType(User user) {
		boolean res = userRepo.existsById(user.getEmail());
		System.out.println(res);
		System.out.println(user.getEmail());
		
		if(res) {
			return "User details didn't store...\nYou have already Registered...";
		}
		else {
			userRepo.save(user);
			return "User("+ user.getName() +") Registered successfully...";
		}
	}


// ==========================================================================================================

		//  Retrieve Message by Email & password | Login Operation :-   Op: 7

		public String loginOperation(User user) {
			String email = user.getEmail();
			String password = user.getPassword();
			
			Optional<User> op = userRepo.findById(user.getEmail());
			System.out.println("**************************"+op);
			
				if(op.isPresent()) {
					User u = op.get();
					
					if(u.getPassword().equals(user.getPassword())) {
						return "WELCOME";
					} else {
						return "Password may be worng";
					}
				} else {
					return "Email or Password may be worng";
				}
		}
			
			
//=====================================================================================================
			
}
