package com.example.demo;

import com.caucho.hessian.io.Hessian2Output;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

@SpringBootTest
class DemoApplicationTests{

	@Autowired
	private TestService testService;

	@Test
	void contextLoads() {

		Class<?> clazz = testService.getApplicationContext().getType("getUser");

		boolean match = testService.getApplicationContext().isTypeMatch("getUser", User.class);

		System.out.println(clazz);
	}


}
