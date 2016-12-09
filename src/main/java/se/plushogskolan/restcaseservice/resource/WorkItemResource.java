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
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;

import se.plushogskolan.casemanagement.model.WorkItem;
import se.plushogskolan.casemanagement.service.CaseService;
import se.plushogskolan.restcaseservice.model.DTOWorkItem;

@Path("workitems")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public final class WorkItemResource {
	
	@Context
	private UriInfo uriInfo;
	
//	DTOWorkItemService service = new DTOWorkItemService();
	
	@Autowired
	CaseService service;
	
	@POST
	public Response addWorkItem(DTOWorkItem dtoWorkItem) {
		WorkItem workItem = dtoWorkItem.toEntity(dtoWorkItem);
		workItem = service.save(workItem);
		URI location = uriInfo.getAbsolutePathBuilder()
				.path(workItem.getId().toString())
				.build();
		return Response.created(location).build();
	}
	
	@PUT
	@Path("{id}")
	public Response updateStatus(@PathParam("id") Long id, @QueryParam("status") String status) {
		WorkItem.Status workItemStatus = stringToStatus(status);
		WorkItem workItem = service.updateStatusById(id, workItemStatus);
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
		WorkItem.Status workItemStatus = stringToStatus(status);
		Slice<WorkItem> slice = service.getWorkItemsByStatus(workItemStatus, new PageRequest(0, 10));
		return slice.getContent();
	}
	
	@GET
	public Collection<WorkItem> getWorkItemByTeamId(@QueryParam("teamId") Long teamId) {
		Slice<WorkItem> slice = service.getWorkItemsByTeamId(teamId, new PageRequest(0, 10));
		return slice.getContent();
	}
	
	@GET
	public Collection<WorkItem> getWorkItemByUserId(@QueryParam("userId") Long userId) {
		Slice<WorkItem> slice = service.getWorkItemsByUserId(userId, new PageRequest(0, 10));
		return slice.getContent();
	}

	@GET
	public Collection<WorkItem> searchWorkItemDescription(@QueryParam("description") String description) {
		Slice<WorkItem> slice = service.searchWorkItemByDescription(description, new PageRequest(0, 10));
		return slice.getContent();
	}
	
	@GET
	public Collection<WorkItem> getWorkItemsWithIssue(@QueryParam("withissue") boolean withIssue) throws Exception {
		if(withIssue) {
			Slice<WorkItem> slice = service.getWorkItemsWithIssue(new PageRequest(0, 10));
			return slice.getContent();
		}
		else
			throw new Exception();
	}
	
	private WorkItem.Status stringToStatus(String value) {
		WorkItem.Status status = null;
		
		if(value.equals("done") || value.equals("DONE"))
			status = WorkItem.Status.DONE;
		else if(value.equals("unstarted") || value.equals("UNSTARTED"))
			status = WorkItem.Status.UNSTARTED;
		else if(value.equals("started") || value.equals("STARTED"))
			status = WorkItem.Status.STARTED;
		
		return status;
	}
}
