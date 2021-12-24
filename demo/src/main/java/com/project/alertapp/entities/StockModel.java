package com.project.alertapp.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

/**
 * This entity contains properties for stocks and initialize automatically id, createdDate, updatedDate and deleted properties.
 * @author KaanSarigul
 *
 */
@Entity
@Data
public class StockModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String stockName;
	private String stockSymbol;
	private boolean deleted = false;
	
	@JsonFormat(pattern="yyyy/MM/dd HH:mm:ss")
	private Date createdDate = new Date(System.currentTimeMillis());
	
	@JsonFormat(pattern="yyyy/MM/dd HH:mm:ss")
	private Date updatedDate = createdDate;
	
}