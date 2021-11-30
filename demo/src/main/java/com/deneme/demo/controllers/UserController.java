package com.deneme.demo.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.deneme.demo.entities.User;
import com.deneme.demo.requests.SoftDeleteRequest;
import com.deneme.demo.requests.UpdatePasswordRequest;
import com.deneme.demo.requests.UpdateUserRequest;
import com.deneme.demo.services.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	
	private UserService userService;
	
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	@GetMapping
	public List<User> getAllUser(){
		return userService.getAllUsers();
	}
	
	@GetMapping("/{userId}")
	public User getOneUser(@PathVariable Long userId) {
		return userService.getOneUser(userId);
	}
	
	@DeleteMapping("/delete/{userId}")
	public void deleteUser(@PathVariable Long userId) {
		userService.deleteUser(userId);
	}
	@PutMapping("/{userId}/delete")
	public User softDeleteUser(@PathVariable Long userId,@RequestBody SoftDeleteRequest deleteRequest) {
		return userService.softDeleteUser(userId, deleteRequest);
	}
	@PutMapping("/{userId}")
	public User updateUser(@PathVariable Long userId,@RequestBody UpdateUserRequest updateUser) {
		return userService.updateUser(userId, updateUser);
	}
	@PutMapping("/{userId}/changePassword")
	public User updateUserPassword(@PathVariable Long userId,@RequestBody UpdatePasswordRequest updatePassword) {
		return userService.updatePassword(userId, updatePassword);
	}
	
	
}