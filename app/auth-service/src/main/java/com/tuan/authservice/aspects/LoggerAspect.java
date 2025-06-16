package com.tuan.authservice.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Aspect
@Slf4j
@Component
public class LoggerAspect  {
    @Pointcut("excution(*com.tuan.authservice..*.*(..))")
    public void agentPointCut(){}
    
    @Around(value = "AgentPointCut")
    public Object logAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Long start = System.currentTimeMillis();
        proceedingJoinPoint.proceed();
        Long duration = System.currentTimeMillis() - start;
        log.info("method: "+proceedingJoinPoint.getSignature().getName()+ "run in: "+ duration + "ms");
        return new Object();
    }

    @AfterThrowing(value = "AgentPointCut", throwing = "e")
    public void logAfterThrowing (JoinPoint joinPoint, Exception e) {
        e.printStackTrace();
    }

}
