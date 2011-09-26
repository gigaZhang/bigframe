package com.ldl.bigframe.web.exception;

/**
 * 用于在Action中抛出提示信息异常，跳转到info.jsp页面
 * @author nandi.ldl
 *
 */
public class InfoException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public InfoException() {
		super();
	}

	public InfoException(String message) {
		super(message);
	}
	public InfoException(String message, Throwable cause) {
		super(message, cause);
	}
	public InfoException(Throwable cause) {
		super(cause);
	}
}
