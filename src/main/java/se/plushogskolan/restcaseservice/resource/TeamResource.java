package se.plushogskolan.restcaseservice.resource;

import java.net.URI;
import java.util.Collection;
import java.util.List;

import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
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
import javax.ws.rs.core.Response.Status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import se.plushogskolan.casemanagement.model.Team;
import se.plushogskolan.casemanagement.model.WorkItem;
import se.plushogskolan.restcaseservice.model.DTOTeam;
import se.plushogskolan.restcaseservice.model.DTOUser;
import se.plushogskolan.restcaseservice.model.PageRequestBean;
import se.plushogskolan.restcaseservice.model.UsersRequestBean;
import se.plushogskolan.restcaseservice.service.DTOTeamService;
import se.plushogskolan.restcaseservice.service.DTOUserService;
import se.plushogskolan.restcaseservice.service.DTOWorkItemService;

@Component
@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN })
@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
@Path("teams")
public class TeamResource {

	@Context
	private UriInfo uriInfo;

	@Autowired
	DTOTeamService service;

	@Autowired
	DTOUserService userService;
	
	@Autowired
	DTOWorkItemService workItemService;

	@POST
	public Response addTeam(DTOTeam dtoTeam) {

		Team team = service.save(dtoTeam);

		URI location = uriInfo.getAbsolutePathBuilder().path(team.getId().toString()).build();

		return Response.created(location).build();

	}

	@PUT
	@Path("{id}")
	public Response updateTeam(@PathParam("id") Long teamId, DTOTeam dtoTeam) {

		if (dtoTeam.getIsActive() != null) {
			service.activateTeam(teamId, dtoTeam.getIsActive());
		}
		if(dtoTeam.getName() != null){
			service.update(teamId, dtoTeam);
		}
		

		return Response.status(Status.NO_CONTENT).build();
	}

	@GET
	@Path("{id}")
	public Team getTeamById(@PathParam("id") Long teamId) {
		Team team = service.getTeam(teamId);
		return team;
	}

	@GET
	public List<Team> searchTeamByName(@QueryParam("name") String name, @BeanParam PageRequestBean pageRequestBean) {

		List<Team> teams = service.searchTeamByName(name, pageRequestBean.getPage(), pageRequestBean.getSize());

		return teams;
	}

	@GET
	@Path("{id}/users")
	public Response getUsersByTeam(@BeanParam UsersRequestBean bean, @PathParam("id") Long id) {

		List<DTOUser> dtoList = userService.getUsersByTeam(id, bean.getPage(), bean.getSize());

		return Response.ok(dtoList).build();
	}
	
	@GET
	@Path("{id}/workitems")
	public Collection<WorkItem> getWorkItemsByTeamId(@PathParam("id") Long id, @BeanParam PageRequestBean pageRequest) {
		return workItemService.getWorkItemsByTeamId(id, pageRequest.getPage(), pageRequest.getSize());
	}
	
	@PUT
	@Path("{id}/users")
	public Response addUserToTeam(DTOUser dtoUser, @PathParam("id") Long teamId){
		
		userService.addUserToTeam(dtoUser.getId(), teamId);
		
		return Response.status(Status.NO_CONTENT).build();
	}

}
