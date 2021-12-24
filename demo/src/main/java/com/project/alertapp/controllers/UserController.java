package com.project.alertapp.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.alertapp.entities.User;
import com.project.alertapp.requests.SoftDeleteRequest;
import com.project.alertapp.requests.UpdatePasswordRequest;
import com.project.alertapp.requests.UpdateUserRequest;
import com.project.alertapp.services.UserService;


/**
 * This class provides REST request for alerts.
 * @author KaanSarigul
 *
 */
@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;
	
	/**
	 * This method sends a request to userService to get all user.
	 * @return Returns list of users.
	 */
	@GetMapping
	public List<User> getAllUser(){
		return userService.getAllUsers();
	}
	
	/**
	 * This method sends a request to userService to get one user with userId parameter.
	 * @param userId Id of requested user.
	 * @return Returns requested user.
	 */
	@GetMapping("/{userId}")
	public User getOneUser(@PathVariable Long userId) {
		return userService.getOneUser(userId);
	}
	
	/**
	 * This method sends a request to userService to delete one user with userId parameter.
	 * @param userId Id of requested user.
	 */
	@DeleteMapping("/{userId}/delete")
	public void deleteUser(@PathVariable Long userId) {
		userService.deleteUser(userId);
	}
	
	/**
	 * This method sends a request to userService to soft delete one user with userId parameter.
	 * @param userId Id of requested user.
	 * @param deleteRequest SoftDeleteRequest object contains soft delete situation for an object.
	 * @return  Returns updated user.
	 */
	@PutMapping("/{userId}/delete")
	public User softDeleteUser(@PathVariable Long userId,@RequestBody SoftDeleteRequest deleteRequest) {
		return userService.softDeleteUser(userId, deleteRequest);
	}
	/**
	 * This method sends a request to userService to update user with userId parameter. 
	 * @param userId Id of requested user.
	 * @param updateUser Contains user data, cannot be null.
	 * @return Returns updated user.
	 */
	@PutMapping("/{userId}")
	public User updateUser(@PathVariable Long userId,@RequestBody UpdateUserRequest updateUser) {
		return userService.updateUser(userId, updateUser);
	}
	/**
	 * This method sends a request to userService to change user password with userId parameter.
	 * @param userId Id of requested user.
	 * @param updatePassword Contains new password data, cannot be null.
	 * @return Returns updated data.
	 */
	@PutMapping("/{userId}/changePassword")
	public User updateUserPassword(@PathVariable Long userId,@RequestBody UpdatePasswordRequest updatePassword) {
		return userService.updatePassword(userId, updatePassword);
	}
	
	
}