package com.kth.boardAndFavorite.board.advice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.kth.boardAndFavorite.common.error.ErrorResult;
import com.kth.boardAndFavorite.common.exception.AuthenticationException;
import com.kth.boardAndFavorite.common.exception.NonAuthorizationException;

@RestControllerAdvice("com.callbus.zaritalk.board.controller")
public class BoardExceptionAdvice {
	
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	@ExceptionHandler(NonAuthorizationException.class)
	public ErrorResult authorizationExHandler(NonAuthorizationException e) {
		return new ErrorResult(HttpStatus.UNAUTHORIZED.value(),e.getMessage());
	}
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(AuthenticationException.class)
	public ErrorResult authenticationExHandler(AuthenticationException e) {
		return new ErrorResult(HttpStatus.BAD_REQUEST.value(),e.getMessage());
	}
	
}
