package com.callbus.zaritalk.common.aop;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.callbus.zaritalk.common.exception.NonAuthorizationException;

import lombok.extern.slf4j.Slf4j;

@Aspect
@Component
@Slf4j
public class AuthCheckAop {
	
	@Around("@annotation(com.callbus.zaritalk.common.annotation.AuthCheck)")
	public Object checkAuthorization(ProceedingJoinPoint point) throws Throwable{
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		String authorization ="";
		if(request != null) 
			authorization = request.getHeader("Authorization");
		if(authorization == null || authorization.isBlank())
			throw new NonAuthorizationException();
		return point.proceed();
	}
}
