package com.kth.boardAndFavorite.common.resolver;

import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.kth.boardAndFavorite.common.annotation.GetCustomer;
import com.kth.boardAndFavorite.customer.domain.Customer;
import com.kth.boardAndFavorite.customer.service.CustomerService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Component
@Slf4j
public class GetCustomerArgumentResolver implements HandlerMethodArgumentResolver {

	private final CustomerService customerService;
	
	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		return parameter.hasParameterAnnotation(GetCustomer.class) && parameter.getParameterType().equals(Customer.class) ;
	}

	@Override
	public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
			NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
		String authorization = webRequest.getHeader("Authorization");
		if(authorization != null) {
			String[] split = authorization.split(" ");
			return customerService.getOne(Long.valueOf(split[1]));
		}
		return null;
	}

}
