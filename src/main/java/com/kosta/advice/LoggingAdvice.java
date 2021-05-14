package com.kosta.advice;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect //PointCut, Advice
public class LoggingAdvice {
	Logger logger = LoggerFactory.getLogger(LoggingAdvice.class);
	@Pointcut("execution(* com.kosta.business..*ServiceImpl.*(..)) or "+
			  "execution(* com.kosta.business..*DAOMybatis.*(..))")
	public void targetMethod() {   
		
	}
	
	@Around("targetMethod()")
	public Object aroundMethod(ProceedingJoinPoint joinPoint) throws Throwable {
		String methodName = joinPoint.getSignature().getName();
		logger.info("[호출전] : LoggingAdvice" + methodName );
		//before수행
		Object object = joinPoint.proceed();
		logger.info("[호출후] LoggingAdvice: " + joinPoint );
		//after수행
		return object;
	}
	@Before("targetMethod()")
	public void beforeMethod(JoinPoint joinpoint) {
		logger.info("------------before-----------------");
		logger.info("모든 파라메타들:{}", Arrays.toString(joinpoint.getArgs()));
		logger.info("Advice타입:{}", joinpoint.getKind());
		logger.info("대상객체의 메서드정보:" + joinpoint.getSignature().getName());
		logger.info("대상객체:" + joinpoint.getTarget().toString());
		logger.info("Advice를 행하는 객체:" + joinpoint.getThis().toString());
	}
	@After("targetMethod()")
	public void afterMethod(JoinPoint joinpoint) {
		logger.info("------------after-----------------");
		logger.info("모든 파라메타들:{}", Arrays.toString(joinpoint.getArgs()));
		logger.info("Advice타입:{}", joinpoint.getKind());
		logger.info("대상객체의 메서드정보:" + joinpoint.getSignature().getName());
		logger.info("대상객체:" + joinpoint.getTarget().toString());
		logger.info("Advice를 행하는 객체:" + joinpoint.getThis().toString());
	}
	
}
