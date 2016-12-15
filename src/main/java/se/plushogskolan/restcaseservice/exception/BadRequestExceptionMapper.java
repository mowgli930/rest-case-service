package se.plushogskolan.restcaseservice.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public final class BadRequestExceptionMapper implements ExceptionMapper<BadRequestException> {

	@Override
	public Response toResponse(BadRequestException badRequestException) {
		
		return Response.status(Status.BAD_REQUEST).entity(badRequestException.getMessage()).build();
	}

}
