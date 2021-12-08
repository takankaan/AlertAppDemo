package com.deneme.demo.requests;

import java.util.Date;

import lombok.Data;

@Data
public class UpdatePasswordRequest {
	
	private String oldPassword;
	private String newPassword;
	
	Date updatedDate = new Date(System.currentTimeMillis());

}
