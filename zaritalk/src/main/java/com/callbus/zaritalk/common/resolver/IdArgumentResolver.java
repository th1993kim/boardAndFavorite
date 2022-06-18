package com.callbus.zaritalk.common.resolver;

import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.callbus.zaritalk.common.annotation.Id;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class IdArgumentResolver implements HandlerMethodArgumentResolver {

	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		return parameter.hasParameterAnnotation(Id.class) && parameter.getParameterType().equals(Long.class) ;
	}

	@Override
	public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
			NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
		String authorization = webRequest.getHeader("Authorization");
		if(authorization != null) {
			String[] split = authorization.split(" ");
			return Long.valueOf(split[1]);
		}
		return null;
	}

}
