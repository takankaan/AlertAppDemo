package com.project.alertapp.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.alertapp.entities.User;

/**
 * This repository uses for user processes on database.
 * @author KaanSarigul
 *
 */
public interface UserRepository extends JpaRepository<User, Long> {
	
	/**
	 * This method retrieves one user from database by using TcNo.
	 * @param tcNo TcNo is unique key belongs to a user.
	 * @return Returns one user.
	 */
	User findByTcNo(Long tcNo);
	/**
	 * This method checks if the TcNo exists.
	 * @param tcNo TcNo is unique key belongs to a user.
	 * @return If TcNo exists returns true, else false.
	 */
	boolean existsByTcNo(Long tcNo);
}