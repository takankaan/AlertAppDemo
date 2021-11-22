package com.deneme.demo.requests;

import lombok.Data;

@Data
public class LoginRequest {

	private Long tcNo;
	private String hashPassword;
}
