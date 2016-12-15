package se.plushogskolan.restcaseservice.exception;

public final class ConflictException extends RuntimeException {

	private static final long serialVersionUID = 3521770364975472112L;

	public ConflictException(String message) {
		super(message);
	}

	public ConflictException(String message, Exception e) {
		super(message, e);
	}

}
