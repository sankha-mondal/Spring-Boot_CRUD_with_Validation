package com.controller;



import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.entity.Employee;

@RestController
public class EmplooyeeController {
	
	//  http://localhost:8484/store_employees
	
	@PostMapping("/store_employees")
	public ResponseEntity<Employee> saveEmployeeData (@Valid @RequestBody Employee employee) {
		return new ResponseEntity<Employee>(employee, HttpStatus.CREATED);
	}
}

