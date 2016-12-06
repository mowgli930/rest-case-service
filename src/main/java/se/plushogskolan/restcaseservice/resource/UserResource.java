package se.plushogskolan.restcaseservice.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import se.plushogskolan.casemanagement.service.CaseService;

@Component
@Path("users")
public final class UserResource {

	@Autowired
	CaseService service;

	@GET
	public Response hola() {

		return Response.ok().build();
	}

}