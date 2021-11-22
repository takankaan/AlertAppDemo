package com.deneme.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deneme.demo.entities.User;
import com.deneme.demo.repos.UserRepository;
import com.deneme.demo.requests.LoginRequest;
import com.deneme.demo.requests.SoftDeleteRequest;

@Service
public class UserService {
	
	@Autowired
	UserRepository userRepository;
	
	public List<User> getAllUsers(){
		return userRepository.findAll();
	}
	
	public User saveOneUser(User newUser) {
		User user = userRepository.findByTcNo(newUser.getTcNo());
		if(user == null)
		{
			newUser.setHashPassword(PasswordHashing.hashPassword(newUser.getHashPassword()));
			return userRepository.save(newUser);
		}
		else
			return null;
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
	
	public User loginUser(LoginRequest loginRequest) {
		User user = userRepository.findByTcNo(loginRequest.getTcNo());
		if(user != null)
		{
			if(user.getHashPassword().equals(PasswordHashing.hashPassword(loginRequest.getHashPassword())));
				return user;
		}
		return null;
	}
}
