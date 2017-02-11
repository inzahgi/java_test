package com.inzahgi;

/**
 * @author kai-zheng
 * @version 1.0.0
 * @date 2015-11-21 17:05
 * @since 1.0.0
 */
public class DemoServiceImpl implements DemoService {

    public String sayHello(String name) {
        return "Hello " + name;
    }

}
