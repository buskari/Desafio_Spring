package br.com.mercadolivre.controller.advice;

public class EntityAlreadyExistsException extends RuntimeException {

	private static final long serialVersionUID = -5925783604483219420L;

	public EntityAlreadyExistsException() {
		super();
	}

	public EntityAlreadyExistsException(String message, Throwable cause, boolean enableSuppression,
										boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public EntityAlreadyExistsException(String message, Throwable cause) {
		super(message, cause);
	}

	public EntityAlreadyExistsException(String message) {
		super(message);
	}

	public EntityAlreadyExistsException(Throwable cause) {
		super(cause);
	}

}
