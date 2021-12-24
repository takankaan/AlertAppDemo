package com.project.alertapp.requests;

import lombok.Data;

/**
 * This entity designed for users login requests. It contains tcNo and hashPassword.
 * @author KaanSarigul
 *
 */
@Data
public class LoginRequest {

	private Long tcNo;
	private String hashPassword;
}
