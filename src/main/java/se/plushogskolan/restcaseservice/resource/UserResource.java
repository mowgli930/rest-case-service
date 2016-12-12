package se.plushogskolan.restcaseservice.resource;

import java.net.URI;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import se.plushogskolan.casemanagement.model.User;
import se.plushogskolan.restcaseservice.exception.ConflictException;
import se.plushogskolan.restcaseservice.model.DTOUser;
import se.plushogskolan.restcaseservice.service.DTOUserService;

@Component
@Path("users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public final class UserResource {

	@Autowired
	DTOUserService userService;

	@Context
	private UriInfo uriInfo;

	@POST
	public Response addUser(DTOUser dtoUser) throws ConflictException {

		User savedUser = userService.saveUser(dtoUser);

		URI location = uriInfo.getAbsolutePathBuilder().path(savedUser.getId().toString()).build();

		return Response.created(location).build();
	}
	
	@PUT
	@Path("{id}")
	public Response updateUserFirstName(@PathParam("id") Long id, DTOUser dtoUser){
		
		if(!dtoUser.getFirstName().isEmpty())
			userService.updateUserFirstName(id, dtoUser.getFirstName());
		
		if(!dtoUser.getLastName().isEmpty())
			userService.updateUserLastName(id, dtoUser.getLastName());
		
		
		
		
		return Response.status(Status.NO_CONTENT).build();
	}

}