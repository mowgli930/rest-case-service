package se.plushogskolan.restcaseservice.exception;

public final class NotFoundException extends RuntimeException {

	private static final long serialVersionUID = -3090252089555397600L;
	
	public NotFoundException(String message){
		super(message);
	}
	
	public NotFoundException(String message, Exception e){
		super(message, e);
	}

}
