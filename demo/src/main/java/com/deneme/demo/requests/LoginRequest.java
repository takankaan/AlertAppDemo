package com.deneme.demo.requests;

public class LoginRequest {

	private Long tcNo;
	private String hashPassword;
	
	public Long getTcNo() {
		return tcNo;
	}
	public void setTcNo(Long tcNo) {
		this.tcNo = tcNo;
	}
	public String getHashPassword() {
		return hashPassword;
	}
	public void setHashPassword(String hashPassword) {
		this.hashPassword = hashPassword;
	}
	
	
}
