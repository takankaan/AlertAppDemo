package com.deneme.demo.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Entity
@Data
public class Alerts {

	@Id
	Long id;
	
	Long userId;
	
	Long stockId;
	
	float alertPrice;
	
	@JsonFormat(pattern="yyyy/MM/dd HH:mm:ss")
	Date createdDate = new Date(System.currentTimeMillis());
	
	@JsonFormat(pattern="yyyy/MM/dd HH:mm:ss")
	Date updatedDate = createdDate; 
	
	boolean alertDirection;
	boolean deleted = false;
	
	
}
