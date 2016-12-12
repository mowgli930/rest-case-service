package se.plushogskolan.restcaseservice.resource;


import java.net.URI;
import java.util.List;

import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import se.plushogskolan.casemanagement.model.Issue;
import se.plushogskolan.restcaseservice.model.DTOIssue;
import se.plushogskolan.restcaseservice.model.PageRequestBean;
import se.plushogskolan.restcaseservice.service.DTOIssueService;

@Component
@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN})
@Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
@Path("issues")
public class IssueResource {
	
	@Context
	private UriInfo uriInfo;

	@Autowired
	DTOIssueService service;
	
	@POST
	public Response addIssue(DTOIssue dtoIssue, Long workItemId){
		Issue issue = service.save(dtoIssue, workItemId);
		
		URI location = uriInfo.getAbsolutePathBuilder()
				.path(issue.getId().toString())
				.build();
		
		return Response.created(location).build();
	}
	
	@PUT
	@Path("{id}")
	public Response updateIssue(@PathParam("id") Long id, @QueryParam("description") String description){
		Issue issue = service.updateDescription(id, description);
		return Response.ok(issue).build();
	}
	
	@GET
	@Path("{id}")
	public Issue getIssue(@PathParam("id") Long id){
		Issue issue = service.getIssue(id);
		return issue;
	}
	
	@GET
	public List<Issue> getAllIssues(@BeanParam PageRequestBean pageRequestBean){
		List<Issue> issues = service.getAllIssues(pageRequestBean.getPage(), pageRequestBean.getSize());
		return issues;
	}
	
	
}
