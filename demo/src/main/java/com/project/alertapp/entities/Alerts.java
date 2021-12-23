package com.project.alertapp.entities;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Entity
@Data
public class Alerts {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Long userId;
	
	private String stockSymbol;
	
	private BigDecimal alertPrice;
	
	@JsonFormat(pattern="yyyy/MM/dd HH:mm:ss")
	private Date createdDate = new Date(System.currentTimeMillis());
	
	@JsonFormat(pattern="yyyy/MM/dd HH:mm:ss")
	private Date updatedDate = createdDate; 
	
	private boolean alertDirection;
	private boolean permanent;
	private boolean deleted = false;
	
	
}
