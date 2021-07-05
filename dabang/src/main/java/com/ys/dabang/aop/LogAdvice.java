package com.ys.dabang.aop;

import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogAdvice {

	private static final Logger log = LoggerFactory.getLogger(LogAdvice.class);

	@Around("execution(* com.ys.ocean.controller..*Controller.*(..))")
	public Object logPrint(ProceedingJoinPoint joinPoint) throws Throwable {

		Object result = joinPoint.proceed();
		log.info("=======================����===========================");

		String type = joinPoint.getSignature().getDeclaringTypeName();

		// �޼��� �̸�
		log.info("��Ʈ�ѷ� + �޼���" + type + "." + joinPoint.getSignature().getName() + "()");

		// �Ű� ����
		log.info("�Ű����� : " + Arrays.deepToString(joinPoint.getArgs()));

		return result;
	}

}
