package com.callbus.zaritalk.common.exception;


public class NonAuthorizationException extends Exception {

	private static final long serialVersionUID = 1L;
	@Override
	public String getMessage() {
		return "인가 에러 - 권한이 부족하여 실행할 수 없습니다.";
	}
}
