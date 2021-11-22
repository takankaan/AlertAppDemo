package com.deneme.demo.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Entity
@Data
public class Stock {

	@Id
	Long id;
	
	String stockName;
	boolean deleted = false;
	
	@JsonFormat(pattern="yyyy/MM/dd HH:mm:ss")
	Date createdDate = new Date(System.currentTimeMillis());
	
	@JsonFormat(pattern="yyyy/MM/dd HH:mm:ss")
	Date updatedDate = createdDate;
	
}
