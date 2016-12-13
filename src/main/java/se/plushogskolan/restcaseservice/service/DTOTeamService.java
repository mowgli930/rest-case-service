package se.plushogskolan.restcaseservice.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import se.plushogskolan.casemanagement.exception.AlreadyPersistedException;
import se.plushogskolan.casemanagement.exception.InternalErrorException;
import se.plushogskolan.casemanagement.model.Team;
import se.plushogskolan.casemanagement.service.CaseService;
import se.plushogskolan.restcaseservice.exception.ConflictException;
import se.plushogskolan.restcaseservice.exception.WebInternalErrorException;
import se.plushogskolan.restcaseservice.model.DTOTeam;

@Component
public class DTOTeamService {

	private final CaseService service;

	@Autowired
	public DTOTeamService(CaseService service) {
		this.service = service;
	}

	public Team save(DTOTeam dtoTeam) {
		try {
			Team team = dtoTeam.toEntity(dtoTeam);
			return service.save(team);
		} catch (AlreadyPersistedException e1) {
			throw new ConflictException("Team already exists");
		} catch (InternalErrorException e2) {
			throw new WebInternalErrorException("server error");
		}
	}

	public Team update(Long dtoTeamId, DTOTeam dtoTeam) {
		try {
			return service.updateTeam(dtoTeamId, dtoTeam.toEntity(dtoTeam));
		} catch (InternalErrorException e) {
			throw new WebInternalErrorException("server error");
		}
	}

	public Team activateTeam(Long dtoTeamId, boolean isActive) {
		try {
			if(isActive){
				return service.activateTeam(dtoTeamId);
			}else {
				return service.inactivateTeam(dtoTeamId);
			}
		} catch (InternalErrorException e) {
			throw new WebInternalErrorException("server error");
		}
	}

	public DTOTeam getTeam(Long dtoTeamId) {
		try {
			return DTOTeam.builder("", true).build().toDTO(service.getTeam(dtoTeamId));
		} catch (InternalErrorException e) {
			throw new WebInternalErrorException("server error");
		}
	}

	public List<DTOTeam> searchTeamByName(String name, int page, int size) {
		try {
			
			return teamListToDTOTeamList(service.searchTeamByName(name, page, size));
		} catch (InternalErrorException e) {
			throw new WebInternalErrorException("server error");
		}
	}

	public List<DTOTeam> getAllTeams(int page, int size) {
		try {
			return teamListToDTOTeamList(service.getAllTeams(page, size));
		} catch (InternalErrorException e) {
			throw new WebInternalErrorException("server error");
		}
	}
	
	private List<DTOTeam> teamListToDTOTeamList(List<Team> list) {

		List<DTOTeam> listDto = new ArrayList<>();

		DTOTeam dtoTeam = DTOTeam.builder("", true).build();

		for (Team user : list) {
			listDto.add(dtoTeam.toDTO(user));
		}

		return listDto;
	}

}
