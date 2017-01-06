package se.plushogskolan.restcaseservice.filter;

import java.io.IOException;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;

import se.plushogskolan.restcaseservice.exception.TokenNotAcceptedException;

public class AuthorizationFilter implements ContainerRequestFilter {

	private final String ACCEPTABLE_TOKEN = "plz-give-VG";

	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		String token = requestContext.getHeaderString("Authorization");
		System.out.println("Does this work as well?\n" + token);
		authorize(token);
	}

	private boolean authorize(String token) {
		if (token != null) {
			if(ACCEPTABLE_TOKEN.equals(token))
				return true;
			else
				throw new TokenNotAcceptedException("Token was not authorized");
		}
		else
			throw new TokenNotAcceptedException("No Authorization token present");
	}
	
}