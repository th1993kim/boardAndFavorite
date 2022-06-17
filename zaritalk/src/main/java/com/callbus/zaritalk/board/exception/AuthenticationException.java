package com.callbus.zaritalk.board.exception;

public class AuthenticationException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String getMessage() {
		return "인증 에러 - ID가 일치하지 않습니다.";
	}

}
