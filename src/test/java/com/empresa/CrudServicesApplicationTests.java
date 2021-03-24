package com.empresa;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootTest
class CrudServicesApplicationTests {

	@Test
	void contextLoads() {
	}
	
	@Autowired
	private BCryptPasswordEncoder byc;
	
	@Test
	void verClave() {
		System.out.println("............"+byc.encode("1234"));
		assertTrue(true);
	}
}
