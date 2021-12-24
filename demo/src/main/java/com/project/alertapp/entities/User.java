package com.project.alertapp.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

/**
 * This entity contains properties for stocks and initialize automatically id, createdDate, updatedDate and deleted properties.
 * @author KaanSarigul
 *
 */
@Entity
@Data
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Long tcNo;
	
	private String name;
	private String surname;
	private String birthPlace;
	private String fatherName;
	private String motherName;
	private String phone;
	private String hashPassword;
	private String mail;
	
	@Temporal (TemporalType.DATE)
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date birthDate;
	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date createdDate = new Date(System.currentTimeMillis());
	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date updatedDate = createdDate;
	
	private boolean deleted = false;


	
}
