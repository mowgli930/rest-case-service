package se.plushogskolan.restcaseservice.resource;

import java.net.URI;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import se.plushogskolan.restcaseservice.model.DTOWorkItem;
import se.plushogskolan.restcaseservice.service.DTOWorkItemService;

@Path("workitems")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public final class WorkItemResource {
	
	@Context
	private UriInfo uriInfo;
	
	DTOWorkItemService service = new DTOWorkItemService();
	
	@POST
	public Response addWorkItem(DTOWorkItem workItem) {
		workItem = service.save(workItem);
		URI location = uriInfo.getAbsolutePathBuilder()
				.path(workItem.getId().toString())
				.build();
		return Response.created(location).build();
	}
	
	@PUT
	@Path("{id}")
	public Response updateStatus(@QueryParam("status") String status) {
		//TODO implement
		
		return null;
	}
	
	@DELETE
	@Path("{id}")
	public Response deleteWorkItem(@PathParam("id") Long id) {
		//TODO implement
		return null;
	}
	
	@GET
	public Response getWorkItemById(@QueryParam("status") String status) {
		//TODO implement
		return null;
	}
	
	@GET
	public Response getWorkItemByTeamId(@QueryParam("teamId") Long teamId) {
		//TODO implement
		return null;
	}
	
	@GET
	public Response getWorkItemByUserId(@QueryParam("userId") Long userId) {
		//TODO implement
		return null;
	}

	@GET
	public Response searchWorkItemDescription(@QueryParam("description") String description) {
		//TODO implement
		return null;
	}
	
	@GET
	public Response getWorkItemsWithIssue(@QueryParam("withissue") boolean withIssue) {
		//TODO implement
		return null;
	}
	
}
