package se.plushogskolan.restcaseservice.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;

public class NotPersistedExceptionMapper implements ExceptionMapper<NotPersistedException> {

	@Override
	public Response toResponse(NotPersistedException notPersistedException) {

		return Response.status(Status.NOT_FOUND).entity(notPersistedException.getMessage()).build();
	}

}
