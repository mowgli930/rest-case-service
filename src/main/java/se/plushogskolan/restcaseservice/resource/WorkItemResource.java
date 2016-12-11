package se.plushogskolan.restcaseservice.resource;

import java.net.URI;
import java.util.Collection;
import java.util.List;

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
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import org.springframework.beans.factory.annotation.Autowired;

import se.plushogskolan.casemanagement.model.WorkItem;
import se.plushogskolan.restcaseservice.model.DTOWorkItem;
import se.plushogskolan.restcaseservice.service.DTOWorkItemService;

@Path("workitems")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public final class WorkItemResource {
	
	@Context
	private UriInfo uriInfo;
	
	@Autowired
	DTOWorkItemService service;
	
	@POST
	public Response addWorkItem(DTOWorkItem dtoWorkItem) {
		WorkItem workItem = service.save(dtoWorkItem);
		URI location = uriInfo.getAbsolutePathBuilder()
				.path(workItem.getId().toString())
				.build();
		
		return Response.created(location).build();
	}

	@PUT
	@Path("{id}")
	public Response updateStatus(@PathParam("id") Long id, @QueryParam("status") String status) {
		WorkItem workItem = service.updateStatusById(id, status);
		return Response.ok(workItem).build();
	}
	
	@DELETE
	@Path("{id}")
	public Response deleteWorkItem(@PathParam("id") Long id) {
		service.deleteWorkItem(id);
		return Response.status(Status.NO_CONTENT).build();
	}
	
	@GET
	public Collection<WorkItem> getWorkItemsByStatus(@QueryParam("status") String status) {
		List<WorkItem> list = service.getWorkItemsByStatus(status, 0, 10);
		return list;
	}
	
	@GET
	public Collection<WorkItem> getWorkItemByTeamId(@QueryParam("teamId") Long teamId) {
		List<WorkItem> list = service.getWorkItemsByTeamId(teamId, 0, 10);
		return list;
	}
	
	@GET
	public Collection<WorkItem> getWorkItemByUserId(@QueryParam("userId") Long userId) {
		List<WorkItem> list = service.getWorkItemsByUserId(userId, 0, 10);
		return list;
	}

	@GET
	public Collection<WorkItem> searchWorkItemDescription(@QueryParam("description") String description) {
		List<WorkItem> list = service.searchWorkItemByDescription(description, 0, 10);
		return list;
	}
	
	@GET
	public Collection<WorkItem> getWorkItemsWithIssue(@QueryParam("withissue") boolean withIssue) {
		if(withIssue) {
			List<WorkItem> list = service.getWorkItemsWithIssue(0, 10);
			return list;
		}
		else
			return null;
	}

}
