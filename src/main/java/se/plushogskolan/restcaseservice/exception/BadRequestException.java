package se.plushogskolan.restcaseservice.exception;

public class BadRequestException extends RuntimeException {

	private static final long serialVersionUID = -7125285031609629332L;

	public BadRequestException(String message) {
		super(message);
	}

	public BadRequestException(String message, Exception e) {
		super(message, e);
	}

}
