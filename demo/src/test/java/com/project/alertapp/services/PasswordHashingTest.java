package com.project.alertapp.services;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PasswordHashingTest {
	
	@Test
	public void hashPasswordWithNullParam() {
		assertEquals(null,PasswordHashing.hashPassword(null));
	}
	@Test
	public void hashPasswordWithTrueParam() {
		assertEquals("c6fcd6622c048594008f72f56befec988aaa1dd7",PasswordHashing.hashPassword("sifre"));
	}
	
	
}
