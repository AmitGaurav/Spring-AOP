package com.cisco.aop;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.cisco.aop.profiling.service.SomeService;

public class Main {
    public static void main(String[] args) {
 
        // Create Spring application context that configured by xml.
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:/spring-context.xml");
        
        // Get our service from the spring context (that we want to be profiled).
        SomeService someService = ctx.getBean(SomeService.class);
 
        // Test profiling through methods calls.
 
        for (int i = 0; i < 10; i++) {
        	someService.doShortJob();
        }
        someService.doLongJob();
 
        // Test that profiler also can handle Exceptions in profiled method.
        someService.doSomethingThatThrowsException();
 
        // Close the spring context
        ctx.close();
    }
}