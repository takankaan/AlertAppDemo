package com.project.alertapp.requests;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

/**
 * This entity designed for update user values. It contains name, surname, birth place, father name, mother name, phone, mail, birth date and updated date values.
 * @author KaanSarigul
 *
 */
@Data
public class UpdateUserRequest {
	
	private String name;
	private String surname;
	private String birthPlace;
	private String fatherName;
	private String motherName;
	private String phone;
	private String mail;
	private Date birthDate;
	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	Date updatedDate = new Date(System.currentTimeMillis());

}
