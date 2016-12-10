package se.plushogskolan.restcaseservice.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class ConflictExceptionMapper implements ExceptionMapper<ConflictException> {

	@Override
	public Response toResponse(ConflictException conflictException) {
		
		return Response.status(Status.CONFLICT).entity(conflictException.getMessage()).build();
	}

}
