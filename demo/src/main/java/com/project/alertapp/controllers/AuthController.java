package com.project.alertapp.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.alertapp.entities.User;
import com.project.alertapp.requests.LoginRequest;
import com.project.alertapp.services.UserService;

/**
 * This class provides REST request for user processes.
 * @author KaanSarigul
 *
 */
@RestController
@RequestMapping("/auth")
public class AuthController {
	@Autowired
	private UserService userService;
	
	/**
	 * This method sends request to userService to create new user.
	 * @param newUser User object what contains user data.
	 * @return Returns saved user.
	 */
	@PostMapping("/register")
	public User createUser(@RequestBody User newUser) {
		return userService.saveOneUser(newUser);
	}
	/**
	 * This method sends request to userService to login with loginRequest object.
	 * @param loginRequest The object contains tcNo and password.
	 * @return Returns logged in user.
	 */
	@PostMapping("/login")
	public User loginUser(@RequestBody LoginRequest loginRequest) {
		return userService.loginUser(loginRequest);
	}
}
