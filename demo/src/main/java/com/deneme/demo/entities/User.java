package com.deneme.demo.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Entity
@Data
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	Long tcNo;
	
	String name;
	String surname;
	String birthPlace;
	String fatherName;
	String motherName;
	String phone;
	String hashPassword;
	
	@Temporal (TemporalType.DATE)
	@JsonFormat(pattern="yyyy-MM-dd")
	Date birthDate;
	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	Date createdDate = new Date(System.currentTimeMillis());
	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	Date updatedDate = createdDate;
	
	boolean deleted = false;


	
}
