package com.ying;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.mock.web.test.MockServletConfig;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.XmlWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletException;

public class Test {

	@org.junit.Test
	public void test(){
		ApplicationContext con = new ClassPathXmlApplicationContext("applicationContext.xml");
		System.out.printf(con.getApplicationName());
	}

	/**
	 * servlet入口调用
	 * org.springframework.web.servlet.FrameworkServlet#DEFAULT_CONTEXT_CLASS
	 * 上面这个配置配置了默认的ApplicationContext为XmlWebApplicationContext
	 * @throws ServletException
	 */
	@org.junit.Test
	public void test1() throws ServletException {
		MockServletConfig config = new MockServletConfig("MyApp");
		config.addInitParameter("contextConfigLocation","classpath*:applicationContext.xml");
		DispatcherServlet dispatcherServlet = new DispatcherServlet();
		dispatcherServlet.init(config);
		WebApplicationContext webApplicationContext = dispatcherServlet.getWebApplicationContext();
		GetBeanTest getBeanTest = (GetBeanTest) webApplicationContext.getBean("getBeanTest");
		getBeanTest.showMe();
	}

	/**
	 * spring配置文件解析并加载bean
	 * 使用XmlWebApplicationContext解析
	 */
	@org.junit.Test
	public void test2(){
		XmlWebApplicationContext xmlWebApplicationContext = new XmlWebApplicationContext();

	}
}
