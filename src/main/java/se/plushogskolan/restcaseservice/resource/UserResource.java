package se.plushogskolan.restcaseservice.resource;

import java.net.URI;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.springframework.stereotype.Component;

import se.plushogskolan.casemanagement.model.User;
import se.plushogskolan.restcaseservice.exception.ConflictException;
import se.plushogskolan.restcaseservice.service.DTUserService;

@Component
@Path("users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public final class UserResource {

	DTUserService userService = new DTUserService();

	@Context
	private UriInfo uriInfo;

	@POST
	public Response addUser(User user) throws ConflictException {

		User savedUser = userService.saveUser(user);

		URI location = uriInfo.getAbsolutePathBuilder().path(savedUser.getId().toString()).build();

		return Response.created(location).build();
	}

}