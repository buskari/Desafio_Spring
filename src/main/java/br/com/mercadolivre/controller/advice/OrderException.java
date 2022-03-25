package br.com.mercadolivre.controller.advice;

public class OrderException extends RuntimeException {

	private static final long serialVersionUID = -8154745631788511081L;

	public OrderException() {
		super();
	}

	public OrderException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public OrderException(String message, Throwable cause) {
		super(message, cause);
	}

	public OrderException(String message) {
		super(message);
	}

	public OrderException(Throwable cause) {
		super(cause);
	}
	
}
