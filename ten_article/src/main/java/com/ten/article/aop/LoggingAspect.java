package com.ten.article.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

@Aspect
@Component
public class LoggingAspect {

    @Pointcut("execution(public * com.ten.article.controller.*Controller.*(..))")
    public void printLog() {
    }

    @Before("printLog()")
    public void before(JoinPoint jp) {
        Class<?> clazz = jp.getTarget().getClass();
        String methodName = jp.getSignature().getName();
        String args = Arrays.stream(jp.getArgs()).map(Objects::toString)
                .collect(Collectors.joining(", "));

        Logger logger = LoggerFactory.getLogger(clazz);
        logger.info("Execute '{}({})'", methodName, args);
    }

}
