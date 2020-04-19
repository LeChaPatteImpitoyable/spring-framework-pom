package com.ying;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

public class Test {

    @SuppressWarnings({ "unused", "deprecation" })
    @org.junit.Test
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
    @org.junit.Test
    public void test1() {
        MyThread myThread = new MyThread();
        myThread.setName("AAA");
        myThread.start();
    }

    @org.junit.Test
    public void test2() {
        MyThread myThread = new MyThread();
        myThread.setName("myThread");
        Thread newThread = new Thread(myThread);
        newThread.setName("newThread");
        newThread.start();
    }

    @org.junit.Test
    public void test3() {
//        BeanFactory bf = new XmlBeanFactory(new ClassPathResource("applicationContext.xml"));
//        GetBeanTest bean = (GetBeanTest) bf.getBean("getBeanTest");
//        bean.showMe();
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
