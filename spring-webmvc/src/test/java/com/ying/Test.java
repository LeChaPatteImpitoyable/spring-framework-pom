package com.ying;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.mock.web.MockServletConfig;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;

public class Test {

	@org.junit.Test
	public void test(){
		ApplicationContext con = new ClassPathXmlApplicationContext("applicationContext.xml");
		System.out.printf(con.getApplicationName());
	}

	public void test1() throws ServletException {
		MockServletConfig config = new MockServletConfig("MyApp");
		config.addInitParameter("contextConfigLocation","classpath*:applicationContext.xml");
		DispatcherServlet dispatcherServlet = new DispatcherServlet();
		dispatcherServlet.init(config);
		WebApplicationContext webApplicationContext = dispatcherServlet.getWebApplicationContext();
		GetBeanTest getBeanTest = (GetBeanTest) webApplicationContext.getBean("getBeanTest");
		getBeanTest.showMe();
	}
}
