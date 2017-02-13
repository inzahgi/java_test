package com.inzahgi.dubbo.provider; /**
 * @author kai-zheng
 * @version 1.0.0
 * @date 2015-11-21 17:12
 * @since 1.0.0
 */
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Provider {
    public static void main(String[] args) throws InterruptedException {
        new ClassPathXmlApplicationContext("classpath*:spring-*.xml").start();
        System.out.println("........................Start image tool....................................");

        synchronized (Provider.class){
            Provider.class.wait();
        }
    }
}
