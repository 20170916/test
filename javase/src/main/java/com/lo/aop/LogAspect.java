package com.lo.aop;

import org.aopalliance.intercept.Joinpoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogAspect {
    static final Logger logger = LoggerFactory.getLogger(LogAspect.class);

    @Pointcut("execution(public * com.lo.aop.AImpl.*(..))")
    public void test(){}

    @Around("test()")
    public Object around(ProceedingJoinPoint proceedingJoinPoint){
        Object ret=null;
        logger.info("--------start");
        logger.error("--------start");
        System.out.println("start");
        try {
            ret = proceedingJoinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return ret;
    }


}
