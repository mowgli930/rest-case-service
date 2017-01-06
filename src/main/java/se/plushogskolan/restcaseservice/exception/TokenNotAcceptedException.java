package se.plushogskolan.restcaseservice.exception;

public final class TokenNotAcceptedException extends RuntimeException {

	private static final long serialVersionUID = -5935587542494670918L;

	public TokenNotAcceptedException(String message) {
		super(message);
	}
	
	public TokenNotAcceptedException(String message, Exception e) {
		super(message, e);
	}
}
