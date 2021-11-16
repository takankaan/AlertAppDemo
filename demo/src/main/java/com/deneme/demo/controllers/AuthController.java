package com.deneme.demo.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.deneme.demo.entities.User;
import com.deneme.demo.requests.LoginRequest;
import com.deneme.demo.services.UserService;

@RestController
@RequestMapping("/auth")
public class AuthController {
	
private UserService userService;
	
	public AuthController(UserService userService) {
		this.userService = userService;
	}
	
	
	@PostMapping("/register")
	public User createUser(@RequestBody User newUser) {
		return userService.saveOneUser(newUser);
	}

	@PostMapping("/login")
	public User loginUser(@RequestBody LoginRequest loginRequest) {
		return userService.loginUser(loginRequest);
	}
}
