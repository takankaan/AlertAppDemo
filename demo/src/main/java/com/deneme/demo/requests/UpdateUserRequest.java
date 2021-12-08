package com.deneme.demo.requests;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class UpdateUserRequest {
	
	private String name;
	private String surname;
	private String birthPlace;
	private String fatherName;
	private String motherName;
	private String phone;
	private Date birthDate;
	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	Date updatedDate = new Date(System.currentTimeMillis());

}
