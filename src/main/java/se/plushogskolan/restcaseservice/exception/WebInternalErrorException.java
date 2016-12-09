package se.plushogskolan.restcaseservice.exception;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.Response;

public class WebInternalErrorException extends WebApplicationException {

	private static final long serialVersionUID = 5663992927260108902L;

	public WebInternalErrorException(String message) {
		super(Response.status(Status.INTERNAL_SERVER_ERROR).entity(message).build());
	}
	
}
