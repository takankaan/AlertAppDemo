package com.project.alertapp.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.alertapp.entities.User;
import com.project.alertapp.repos.UserRepository;
import com.project.alertapp.requests.LoginRequest;
import com.project.alertapp.requests.SoftDeleteRequest;
import com.project.alertapp.requests.UpdatePasswordRequest;
import com.project.alertapp.requests.UpdateUserRequest;

/**
 * UserService provides receive, update and delete users.
 * @author KaanSarigul
 *
 */
@Service
public class UserService {
	
	@Autowired
	UserRepository userRepository;
	
	/**
	 * This method retrieves all users from database.
	 * @return Returns list of all users.
	 */
	public List<User> getAllUsers(){
		return userRepository.findAll();
	}
	/**
	 * This method provides to create a new user.
	 * @param newUser It contains user details.The details cannot be null.
	 * @return Returns saved user.
	 */
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
	/**
	 * This method retrieve a user using by user Id.
	 * @param userId Id of requested user.
	 * @return Returns a user using userId.
	 */
	public User getOneUser(Long userId) {
		if(userId !=null)
			return userRepository.findById(userId).orElse(null);
		else return null;
	}
	/**
	 * This method delete a user completely.
	 * @param userId Id of requested user.
	 */
	public void deleteUser(Long userId) {
		if(userId != null)
			if(!userRepository.findById(userId).isEmpty())
			{
				userRepository.deleteById(userId);
				System.out.println(userId + " User successfully removed!");
			}
	}
	
	/**
	 * This method changes deleted property of the user. It means soft delete a user. 
	 * @param userId Id of requested user.
	 * @param deleteRequest It contains deleted property which will change alert's deleted property.
	 * @return Returns updated user.
	 */
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
	/**
	 * This method provides the user to login using their tcNo and password.
	 * @param loginRequest Contains tcNo and password both of them cannot be null.
	 * @return Returns logged in user.
	 */
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
	/**
	 * This method changes user properties using by updateUserRequest object.
	 * @param userId Id of requested user.
	 * @param updateUser UpdateUser object contains new user properties.
	 * @return Returns updated User.
	 */
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
				user.get().setMail(updateUser.getMail());
				return userRepository.save(user.get());
			}
			else
				return null;
		}
		else
			return null;
	}
	
	/**
	 * This method changes user password using by updateAlertRequest object.
	 * @param userId Id of requested user.
	 * @param updatePasswordRequest UpdatePasswordRequest object contains new user password.
	 * @return
	 */
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
