package com.deneme.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deneme.demo.entities.User;
import com.deneme.demo.repos.UserRepository;
import com.deneme.demo.requests.LoginRequest;
import com.deneme.demo.requests.SoftDeleteRequest;
import com.deneme.demo.requests.UpdatePasswordRequest;
import com.deneme.demo.requests.UpdateUserRequest;

@Service
public class UserService {
	
	@Autowired
	UserRepository userRepository;
	
	public List<User> getAllUsers(){
		return userRepository.findAll();
	}
	
	public User saveOneUser(User newUser) {
		if(newUser.getTcNo() != null)
		{
			if(!userRepository.existsByTcNo(newUser.getTcNo()))
			{
				newUser.setHashPassword(PasswordHashing.hashPassword(newUser.getHashPassword()));
				return userRepository.save(newUser);
			}
			else
				return null;
		}
		else
			return null;
	}
	
	public User getOneUser(Long userId) {
		if(userId !=null)
			return userRepository.findById(userId).orElse(null);
		else return null;
	}
	
	public void deleteUser(Long userId) {
		if(userId != null)
			if(!userRepository.findById(userId).isEmpty())
			{
				userRepository.deleteById(userId);
				System.out.println(userId + " User successfully removed!");
			}
	}
	
	
	public User softDeleteUser(Long userId,SoftDeleteRequest deleteRequest) {
		Optional<User> user;
		if(userId != null)
		{
			user = userRepository.findById(userId);
			if(user.isPresent())
			{
				user.get().setDeleted(deleteRequest.isDeleted());
				user.get().setUpdatedDate(deleteRequest.getUpdatedDate());
				return userRepository.save(user.get());
			}
			else 
				return null;
		}
		else 
			return null;
	}
	
	public User loginUser(LoginRequest loginRequest) {
		User user = userRepository.findByTcNo(loginRequest.getTcNo());
		if(user != null && loginRequest.getHashPassword() != null)
		{
			if(!user.isDeleted())
			{
				if(user.getHashPassword().equals(PasswordHashing.hashPassword(loginRequest.getHashPassword())))
					return user;
				else
					return null;
			}
			return null;
		}
		return null;
	}

	public User updateUser(Long userId, UpdateUserRequest updateUser) {
		Optional<User> user;
		if(userId != null)
		{
			user = userRepository.findById(userId);
			if(user.isPresent())
			{
				user.get().setName(updateUser.getName());
				user.get().setSurname(updateUser.getSurname());
				user.get().setBirthPlace(updateUser.getBirthPlace());
				user.get().setFatherName(updateUser.getFatherName());
				user.get().setMotherName(updateUser.getMotherName());
				user.get().setPhone(updateUser.getPhone());
				user.get().setBirthDate(updateUser.getBirthDate());
				user.get().setUpdatedDate(updateUser.getUpdatedDate());
				return userRepository.save(user.get());
			}
			else
				return null;
		}
		else
			return null;
	}
	
	public User updatePassword(Long userId, UpdatePasswordRequest updatePasswordRequest) {
		Optional<User> user;
		if(userId != null)
		{
			user = userRepository.findById(userId);
			if(user.isPresent())
			{
				if(PasswordHashing.hashPassword(updatePasswordRequest.getOldPassword()).equals(user.get().getHashPassword()))
				{
					user.get().setHashPassword(PasswordHashing.hashPassword(updatePasswordRequest.getNewPassword()));
					user.get().setUpdatedDate(updatePasswordRequest.getUpdatedDate());
					return userRepository.save(user.get());
				}else
					//Şifre Eşleşmiyor
					return null;
			}else
				//Kullanıcı Bulunamadı
				return null;
		}else
			//UserID boş
			return null;
	}
			
}
