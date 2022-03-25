package br.com.mercadolivre.controller.advice;

public class ProductAlreadyExistException extends RuntimeException {

	private static final long serialVersionUID = -5925783604483219420L;

	public ProductAlreadyExistException() {
		super();
	}

	public ProductAlreadyExistException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public ProductAlreadyExistException(String message, Throwable cause) {
		super(message, cause);
	}

	public ProductAlreadyExistException(String message) {
		super(message);
	}

	public ProductAlreadyExistException(Throwable cause) {
		super(cause);
	}

}
