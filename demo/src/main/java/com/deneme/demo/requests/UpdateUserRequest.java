package com.deneme.demo.requests;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class UpdateUserRequest {
	
	String name;
	String surname;
	String birthPlace;
	String fatherName;
	String motherName;
	String phone;
	Date birthDate;
	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	Date updatedDate = new Date(System.currentTimeMillis());

}
