package com.ying;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {

	@org.junit.Test
	public void test(){
		ApplicationContext con = new ClassPathXmlApplicationContext("applicationContext.xml");
		System.out.printf(con.getApplicationName());
	}
}
