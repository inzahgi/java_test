package com.inzahgi.consumer;

/**
 * @author kai-zheng
 * @version 1.0.0
 * @date 2015-11-21 19:04
 * @since 1.0.0
 */


import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Consumer {
    public static void main(String[] args) throws Exception {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[] {"classpath*:spring-*.xml"});
        context.start();

        DemoService demoService = (DemoService)context.getBean("demoService"); // ��ȡԶ�̷������
        String hello = demoService.sayHello("world"); // ִ��Զ�̷���

        System.out.println( hello ); // ��ʾ���ý��
    }
}
