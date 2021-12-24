package com.project.alertapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import springfox.documentation.swagger2.annotations.EnableSwagger2;


/**
 * This class runs the spring application
 * @author KaanSarigul
 * @version 1.0.0
 *
 */
@EnableSwagger2
@SpringBootApplication
public class ApplicationMainClass {
	/**
	 * This method runs project.
	 * @param args Args.
	 */
	public static void main(String[] args) {
		SpringApplication.run(ApplicationMainClass.class, args);
	}
}
