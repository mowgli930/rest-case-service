package se.plushogskolan.restcaseservice.resource;

import java.net.URI;
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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import se.plushogskolan.casemanagement.model.Team;
import se.plushogskolan.restcaseservice.model.DTOTeam;
import se.plushogskolan.restcaseservice.model.DTOUser;
import se.plushogskolan.restcaseservice.model.PageRequestBean;
import se.plushogskolan.restcaseservice.model.UsersRequestBean;
import se.plushogskolan.restcaseservice.service.DTOTeamService;
import se.plushogskolan.restcaseservice.service.DTOUserService;

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

	@POST
	public Response addTeam(DTOTeam dtoTeam) {

		Team team = service.save(dtoTeam);

		URI location = uriInfo.getAbsolutePathBuilder().path(team.getId().toString()).build();

		return Response.created(location).build();

	}

	@PUT
	@Path("{id}")
	public Response updateTeam(@PathParam("id") Long teamId, @QueryParam("name") String name,
			@QueryParam("activate") boolean activate) {
		DTOTeam dtoTeam = new DTOTeam(-1L, "Dummy", true);
		Team teamToUpdate = service.getTeam(teamId);

		teamToUpdate.setName(name);
		dtoTeam = dtoTeam.toDTO(teamToUpdate);
		Team team = service.update(teamId, dtoTeam);

		if (activate == true) {
			team = service.activateTeam(teamId);
		}
		if (activate == false) {
			team = service.inactivateTeam(teamId);
		}

		return Response.ok(team).build();
	}

	@GET
	@Path("{id}")
	public Team getTeamById(@PathParam("id") Long teamId) {
		Team team = service.getTeam(teamId);
		return team;
	}

//	@GET
//	public List<Team> searchTeamByName(@QueryParam("name") String name, @BeanParam PageRequestBean pageRequestBean) {
//
//		List<Team> teams = service.searchTeamByName(name, pageRequestBean.getPage(), pageRequestBean.getSize());
//
//		return teams;
//	}
//
//	@GET
//	public List<Team> getAllTeams(@BeanParam PageRequestBean pageRequestBean) {
//		List<Team> teams = service.getAllTeams(pageRequestBean.getPage(), pageRequestBean.getSize());
//		return teams;
//	}

	@GET
	@Path("{id}/users")
	public Response getUsersByTeam(@BeanParam UsersRequestBean bean, @PathParam("id") Long id) {

		List<DTOUser> dtoList = userService.getUsersByTeam(id, bean.getPage(), bean.getSize());

		return Response.ok(dtoList).build();
	}

}
