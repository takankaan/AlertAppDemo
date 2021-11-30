package com.deneme.demo.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.deneme.demo.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
	User findByTcNo(Long tcNo);

	boolean existsByTcNo(Long tcNo);
}