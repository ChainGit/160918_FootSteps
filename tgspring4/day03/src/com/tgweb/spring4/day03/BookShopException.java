package com.tgweb.spring4.day03;

public class BookShopException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BookShopException() {
		super();
	}

	public BookShopException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public BookShopException(String message, Throwable cause) {
		super(message, cause);
	}

	public BookShopException(String message) {
		super(message);
	}

	public BookShopException(Throwable cause) {
		super(cause);
	}

}
