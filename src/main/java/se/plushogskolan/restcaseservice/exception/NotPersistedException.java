package se.plushogskolan.restcaseservice.exception;

public class NotPersistedException extends RuntimeException {

	private static final long serialVersionUID = -3090252089555397600L;
	
	public NotPersistedException(String message){
		super(message);
	}
	
	public NotPersistedException(String message, Exception e){
		super(message, e);
	}

}
