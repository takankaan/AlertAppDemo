package com.project.alertapp.requests;

import java.util.Date;

import lombok.Data;

/**
 * This entity designed for update user password. It contains old and new passwords and updated date value. 
 * @author KaanSarigul
 *
 */
@Data
public class UpdatePasswordRequest {
	
	private String oldPassword;
	private String newPassword;
	
	Date updatedDate = new Date(System.currentTimeMillis());

}
