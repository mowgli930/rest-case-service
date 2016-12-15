package se.plushogskolan.restcaseservice.resource;


import java.util.List;

import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import se.plushogskolan.restcaseservice.model.DTOIssue;
import se.plushogskolan.restcaseservice.model.PageRequestBean;
import se.plushogskolan.restcaseservice.service.IssueService;

@Component
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("issues")
public final class IssueResource {
	
	@Context
	private UriInfo uriInfo;

	@Autowired
	private IssueService service;
	
	@PUT
	@Path("{id}")
	public Response updateIssue(@PathParam("id") Long id, @QueryParam("description") String description){
		service.updateDescription(id, description);
		return Response.status(Status.NO_CONTENT).build();
	}
	
	@GET
	@Path("{id}")
	public Response getIssue(@PathParam("id") Long id){
		DTOIssue issue = service.getIssue(id);
		return Response.ok(issue).build();
	}
	
	@GET
	public Response getAllIssues(@BeanParam PageRequestBean pageRequestBean){
		List<DTOIssue> issues = service.getAllIssues(pageRequestBean.getPage(), pageRequestBean.getSize());
		return Response.ok(issues).build();
	}
	
	
}
