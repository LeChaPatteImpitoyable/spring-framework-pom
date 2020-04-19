/*
 * Copyright 2002-2018 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.ying;

import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

/**
 *
 * @author Administrator
 * @since 4.3
 */
public class MyTest {

	@SuppressWarnings({ "unused", "deprecation" })
	@Test
	public void test() {
		System.out.println("加载配置文件");
		Resource resource = new ClassPathResource("applicationContext.xml");
		System.out.println("初始化bean");
		BeanFactory bf = new XmlBeanFactory(resource);
		if(bf != null) {
			System.out.println("从BeanFactory中获取bean");
			MyTestBean bean = (MyTestBean) bf.getBean("myTestBean");
			System.out.println(bean.getTestStr());
		}else {
			System.out.println("bf is null!");
		}
	}

	/*
	 * 测试线程中this 和 Thread.currentThread() 获取到线程的区别
	 * */
	@Test
	public void test1() {
		MyThread myThread = new MyThread();
		myThread.setName("AAA");
		myThread.start();
	}

	@Test
	public void test2() {
		MyThread myThread = new MyThread();
		myThread.setName("myThread");
		Thread newThread = new Thread(myThread);
		newThread.setName("newThread");
		newThread.start();
	}

	public static void main(String[] args) {
		new MyTest().test3();
	}

	@Test
	public void test3() {
		BeanFactory bf = new XmlBeanFactory(new ClassPathResource("applicationContext.xml"));
		GetBeanTest bean = (GetBeanTest) bf.getBean("getBeanTest");
		bean.showMe();
	}

	class MyThread extends Thread {

		public MyThread(){

			System.out.println("------" + "构造函数开始" + "------");
			System.out.println("Thread.currentThread().getName() = " + Thread.currentThread().getName());
			System.out.println("Thread.currentThread().isAlive() = " + Thread.currentThread().isAlive());
			System.out.println("this.getName() = " + this.getName());
			System.out.println("this.isAlive() = " + this.isAlive());
			System.out.println("------" + "构造函数结束" + "------");

		}

		@Override
		public void run(){

			Thread testThread = Thread.currentThread();

			System.out.println("------" + "run()开始" + "------");
			System.out.println("Thread.currentThread().getName() = " + testThread.getName());
			System.out.println("Thread.currentThread().isAlive() = " + testThread.isAlive());
			System.out.println("this.getName() = " + this.getName());
			System.out.println("this.isAlive() = " + this.isAlive());
			System.out.println("Thread.currentThread() == this : " + (testThread == this));
			System.out.println("------" + "run()结束" + "------");

		}

	}
}
