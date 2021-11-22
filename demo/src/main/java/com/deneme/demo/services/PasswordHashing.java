package com.deneme.demo.services;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordHashing {
	
	public static String hashPassword (String password) {
	
		MessageDigest messageDigest;
		try {
			messageDigest = MessageDigest.getInstance("SHA");
			messageDigest.update(password.getBytes());
			byte [] resultByArray = messageDigest.digest();
			StringBuilder sb = new StringBuilder();
			for(byte b : resultByArray) {
				sb.append(String.format("%02x", b));
			}
			
			return sb.toString();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			return null;
		}
		
	}
}
