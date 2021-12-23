package com.project.alertapp.requests;

import lombok.Data;

@Data
public class LoginRequest {

	private Long tcNo;
	private String hashPassword;
}
