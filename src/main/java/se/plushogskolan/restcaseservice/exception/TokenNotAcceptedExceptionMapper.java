package se.plushogskolan.restcaseservice.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public final class TokenNotAcceptedExceptionMapper implements ExceptionMapper<TokenNotAcceptedException> {

	@Override
	public Response toResponse(TokenNotAcceptedException tokenNotAcceptedException) {
		return Response.status(Status.UNAUTHORIZED).entity(tokenNotAcceptedException).build();
	}

}
