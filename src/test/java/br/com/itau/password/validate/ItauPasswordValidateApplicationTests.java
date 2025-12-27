package br.com.itau.password.validate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class ItauPasswordValidateApplicationTests {

	@Autowired
	private ItauPasswordValidateApplication context;

	@Test
	void contextLoads() {
		assertNotNull(context, "The context application don`t should be null");
	}
}
