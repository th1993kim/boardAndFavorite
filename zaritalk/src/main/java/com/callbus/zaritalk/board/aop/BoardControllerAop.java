package com.callbus.zaritalk.board.aop;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.callbus.zaritalk.board.Exception.NonAuthorizationException;

@Aspect
@Component
public class BoardControllerAop {
	
	@Around("@annotation(AuthCheck)")
	public Object checkAuthorization(ProceedingJoinPoint point) throws Throwable{
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		String authorazation ="";
		if(request != null) 
			authorazation = request.getHeader("Authorization");
		if(authorazation == null && authorazation.isBlank())
			throw new NonAuthorizationException();
		return point.proceed();
	}
}
