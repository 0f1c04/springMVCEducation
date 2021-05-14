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
		logger.info("[ȣ����] : LoggingAdvice" + methodName );
		//before����
		Object object = joinPoint.proceed();
		logger.info("[ȣ����] LoggingAdvice: " + joinPoint );
		//after����
		return object;
	}
	@Before("targetMethod()")
	public void beforeMethod(JoinPoint joinpoint) {
		logger.info("------------before-----------------");
		logger.info("��� �Ķ��Ÿ��:{}", Arrays.toString(joinpoint.getArgs()));
		logger.info("AdviceŸ��:{}", joinpoint.getKind());
		logger.info("���ü�� �޼�������:" + joinpoint.getSignature().getName());
		logger.info("���ü:" + joinpoint.getTarget().toString());
		logger.info("Advice�� ���ϴ� ��ü:" + joinpoint.getThis().toString());
	}
	@After("targetMethod()")
	public void afterMethod(JoinPoint joinpoint) {
		logger.info("------------after-----------------");
		logger.info("��� �Ķ��Ÿ��:{}", Arrays.toString(joinpoint.getArgs()));
		logger.info("AdviceŸ��:{}", joinpoint.getKind());
		logger.info("���ü�� �޼�������:" + joinpoint.getSignature().getName());
		logger.info("���ü:" + joinpoint.getTarget().toString());
		logger.info("Advice�� ���ϴ� ��ü:" + joinpoint.getThis().toString());
	}
	
}
