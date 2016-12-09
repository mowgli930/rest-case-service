package se.plushogskolan.restcaseservice.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import se.plushogskolan.casemanagement.model.Team;
import se.plushogskolan.casemanagement.service.CaseService;
import se.plushogskolan.restcaseservice.model.DTOTeam;

@Component
public class DTOTeamService {
	
	@Autowired
	CaseService service;
	
	public DTOTeam save(DTOTeam dtoTeam){
		Team team = dtoTeam.toEntity(dtoTeam);
		service.save(team);
		return dtoTeam = dtoTeam.toDTO(team);
	}
	
	public DTOTeam update(Long dtoTeamId, DTOTeam dtoTeam){
		Team team = service.updateTeam(dtoTeamId, dtoTeam.toEntity(dtoTeam));
		return dtoTeam.toDTO(team);
	}
	
	public DTOTeam inactivateTeam(Long dtoTeamId){
		DTOTeam dtoTeam = new DTOTeam(-1L, "", true);
		Team team = service.inactivateTeam(dtoTeamId);
		return dtoTeam.toDTO(team);
	}
	
	public DTOTeam activateTeam(Long dtoTeamId){
		DTOTeam dtoTeam = new DTOTeam(-1L, "", true);
		Team team = service.activateTeam(dtoTeamId);
		return dtoTeam.toDTO(team);
	}
	
	public DTOTeam getTeam(Long dtoTeamId){
		DTOTeam dtoTeam = new DTOTeam(-1L, "", true);
		Team team = service.getTeam(dtoTeamId);
		return dtoTeam.toDTO(team);
	}
	
	public List<DTOTeam> searchTeamByName(String name, int page, int size){
		List<DTOTeam> dtoTeams = new ArrayList<>();
		DTOTeam dtoTeam = new DTOTeam(-1L, "", true);
		List<Team> teams = service.searchTeamByName(name, page, size);
		for(Team t : teams){
			dtoTeams.add(dtoTeam.toDTO(t));
		}
		return dtoTeams;
	}
	
	public List<DTOTeam> getAllTeams(int page, int size){
		List<DTOTeam> dtoTeams = new ArrayList<>();
		DTOTeam dtoTeam = new DTOTeam(-1L, "", true);
		List<Team> teams = service.getAllTeams(page, size);
		for(Team t : teams){
			dtoTeams.add(dtoTeam.toDTO(t));
		}
		return dtoTeams;
	}

}
