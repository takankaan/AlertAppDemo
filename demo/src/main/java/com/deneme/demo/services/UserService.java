package com.deneme.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.deneme.demo.entities.User;
import com.deneme.demo.repos.UserRepository;
import com.deneme.demo.requests.SoftDeleteRequest;

@Service
public class UserService {
	
	UserRepository userRepository;

	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	public List<User> getAllUsers(){
		return userRepository.findAll();
	}
	public User saveOneUser(User newUser) {
		return userRepository.save(newUser);
	}
	public User getOneUser(Long userId) {
		return userRepository.findById(userId).orElse(null);
	}
	public void deleteUser(Long userId) {
		userRepository.deleteById(userId);
	}
	public User softDeleteUser(Long userId,SoftDeleteRequest deleteRequest) {
		Optional<User> user = userRepository.findById(userId);
		if(user.isPresent())
		{
			user.get().setDeleted(deleteRequest.isDeleted());
			user.get().setUpdatedDate(deleteRequest.getUpdatedDate());
			User updatedUser = userRepository.save(user.get());
			return updatedUser;
		}
		else {
			return null;
		}
	}
}
