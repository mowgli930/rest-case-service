package se.plushogskolan.restcaseservice.filter;

import java.io.IOException;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.SecurityContext;

public class AuthorizationFilter implements ContainerRequestFilter {

	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		// TODO Auto-generated method stub
		String token = requestContext.getHeaderString("Authorization");
		SecurityContext securityContext = requestContext.getSecurityContext();
		token += "\t" + securityContext.getAuthenticationScheme();
		System.out.println("Does this work as well?\n" + token);
	}

}