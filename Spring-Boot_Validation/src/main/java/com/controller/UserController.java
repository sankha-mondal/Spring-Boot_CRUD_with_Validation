package com.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.entity.User;
import com.exception.ResourceNotFoundException;
import com.payload.ApiResponse;
import com.repository.UserRepository;
import com.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserRepository userRepo;
	
//*************************************************** : CRUD Operation : *****************************************************************	  
//=======================================================================================================================================

		 //  Retrieve Operation:-  Op:1A
	  	 //	 http://localhost:8484/user/getAll
	  
		  @GetMapping("/getAll")
		  public ResponseEntity<List<User>> getAllUser() {
		    List<User> user = new ArrayList<User>();
		
		    	userRepo.findAll().forEach(user::add);
			
			    if (user.isEmpty()) {
			    //  return new ResponseEntity<>(HttpStatus.NO_CONTENT);  OR
			        throw new ResourceNotFoundException("No data found");
			    }
			
			    return new ResponseEntity<>(user, HttpStatus.OK);
		  }
		  
			
//==========================================================================================================
		  
  		  //  Retrieve data by User-Email :-  Op:1B
		  //  http://localhost:8484/user/getUserByEmail/{uEmail}

		  @GetMapping("/getUserByEmail/{uEmail}")
		  public ResponseEntity<User> getUserByEmail(@PathVariable("uEmail") String uEmail) {
			  
			  User user = userRepo.findById(uEmail)
					  .orElseThrow(() -> new ResourceNotFoundException("Not found User with Email = " + uEmail));
		
		    	return new ResponseEntity<>(user, HttpStatus.OK);
		  }
	
//==========================================================================================================
	
		//  Insert Operation | By PK | By User return type:-  Op:2A
		//  http://localhost:8484/user/store_userType
		
		@PostMapping("/store_userType")
		public ResponseEntity<User> storeUser_userType(@Valid @RequestBody User user){
			User savedUser = userService.storeUser_userType(user);
			return new ResponseEntity<User>(savedUser, HttpStatus.CREATED);
		}
	
// =========================================================================================================
	
		//  Insert Operation | By PK | By String return type:-   Op:2B
		//  http://localhost:8484/user/store_stringType
		
		@PostMapping("/store_stringType")
		public String storeUser_stringType(@Valid @RequestBody User user){
			
			return userService.storeUser_stringType(user);	
		}
	
// =========================================================================================================

	  //  Update Operation:-   Op:3
	  //  http://localhost:8484/user/update/{uEmail}
	  
	  @PutMapping("/update/{uEmail}")
	  public ResponseEntity<User> updateUser(@Valid @PathVariable("uEmail") String uEmail, 
			  								 @RequestBody User userReq) {
		  
		  User _user = userRepo.findById(uEmail)
				  .orElseThrow(() -> new ResourceNotFoundException("Not found User with Email = " + uEmail));
	
		    _user.setName(userReq.getName());
		    _user.setPassword(userReq.getPassword());
		//  _user.setBooking(userReq.getBooking());  // Don't do this bcoz it will store null at the time of Update. 
		    
	    return new ResponseEntity<>(userRepo.save(_user), HttpStatus.OK);
	  }
		
		
// =========================================================================================================

		  //  Delete Operation by Id:-   Op:4
		  //  http://localhost:8484/user/delete/{uEmail}
		  
		  @DeleteMapping("/delete/{uEmail}")
		  public ResponseEntity<ApiResponse> deleteUser(@PathVariable("uEmail") String uEmail) {
			  
			  User user_email = userRepo.findById(uEmail)
				        .orElseThrow(() -> new ResourceNotFoundException("Not found User with Email = " + uEmail));
			  
			  userRepo.deleteById(uEmail);
		    
		    // return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			  return new ResponseEntity<ApiResponse>(new ApiResponse("USer details deleted Successfully", true), HttpStatus.OK);
		  }
		  
// =========================================================================================================

  		  //  All Delete Operation:-   Op:5
		  //  http://localhost:8484/user/deleteAll

		  @DeleteMapping("/deleteAll")
		  public ResponseEntity<HttpStatus> deleteAllUser() {
			  
			  userRepo.deleteAll();
		    
		    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		  }

		  
//=======================================================================================================================================
//**************************************************** : User Define : *****************************************************************		  
//======================================================================================================================================
		  
		  
			//  Retrieve Message by Email & password | Login Operation :-   Op: 7
			
			//  http://localhost:8484/user/login
		  
			@PostMapping(value = "login")
			public String loginOperation(@RequestBody User user) {
				
				System.out.println("Controller: "+user.getEmail());
				//Thread.sleep(3000);
				return userService.loginOperation(user);
			}
		  
		  	  
//==========================================================================================================		  	  
// =========================================================================================================
			
			
}
