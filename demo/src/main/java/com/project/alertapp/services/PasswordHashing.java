package com.project.alertapp.services;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * This class provides hashing passwords.
 * @author KaanSarigul
 *
 */
public class PasswordHashing {
	/**
	 * This method takes the user password and makes it unreadable. 
	 * @param password User login password.
	 * @return Returns unreadable password string.
	 */
	public static String hashPassword (String password) {
	if(password != null)
	{
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
	}else
		return null;
		
	}
}
