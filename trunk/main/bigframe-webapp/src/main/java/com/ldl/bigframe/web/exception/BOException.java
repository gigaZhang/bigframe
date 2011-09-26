package com.ldl.bigframe.web.exception;

public class BOException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public BOException() {
		super();
	}

	public BOException(String message) {
		super(message);
	}
	public BOException(String message, Throwable cause) {
		super(message, cause);
	}
	public BOException(Throwable cause) {
		super(cause);
	}
}
