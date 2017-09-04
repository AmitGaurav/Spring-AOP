package com.cisco.aop.profiling.core.profiler;

import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.util.StopWatch;
import org.springframework.util.StopWatch.TaskInfo;

public class SimpleProfiler {
    
    /**
     * Spring AOP 'around' reference method signature is bounded like this, the
     * method name "profile" should be same as defined in spring.xml aop:around
     * section.
     * 
     * @author amigaura
     * 
     **/
    public Object profile(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start(proceedingJoinPoint.toShortString());
        boolean isExceptionThrown = false;
        try {
            // execute the profiled method
            return proceedingJoinPoint.proceed();
        } catch (RuntimeException e) {
            isExceptionThrown = true;
            throw e;
        } finally {
            stopWatch.stop();
            TaskInfo taskInfo = stopWatch.getLastTaskInfo();
            
            // Log the method's profiling result
            String profileMessage = taskInfo.getTaskName() + ": " + taskInfo.getTimeMillis() + " ms" +
                    (isExceptionThrown ? " (thrown Exception)" : "");
            System.out.println(profileMessage);
        }
    }
     
}
