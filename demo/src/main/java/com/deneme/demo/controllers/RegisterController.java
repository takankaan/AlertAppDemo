package com.deneme.demo.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.deneme.demo.entities.User;
import com.deneme.demo.services.UserService;

@RestController
@RequestMapping("/register")
public class RegisterController {
	
private UserService userService;
	
	public RegisterController(UserService userService) {
		this.userService = userService;
	}
	
	@PostMapping
	public User createUser(@RequestBody User newUser) {
		return userService.saveOneUser(newUser);
	}
	
}
