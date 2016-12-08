package se.plushogskolan.restcaseservice.model;

import java.util.ArrayList;
import java.util.Collection;

import se.plushogskolan.casemanagement.model.Team;
import se.plushogskolan.casemanagement.model.User;

public class DTOTeam extends AbstractDTO implements ModelConverter<Team, DTOTeam>{

	private String name;

	private boolean active;

	private Collection<DTOUser> DTOusers;

	public DTOTeam(String name, Long id, Collection<DTOUser> users) {
		super(id);
		this.active = true;
		this.name = name;
		this.DTOusers = users;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + name.hashCode();
		return result;
	}

	@Override
	public boolean equals(Object other) {

		if (this == other) {
			return true;
		}

		if (null == other) {
			return false;
		}

		if (other instanceof DTOTeam) {
			DTOTeam otherTeam = (DTOTeam) other;
			return name.equals(otherTeam.getName()) && active == otherTeam.isActive();
		}
		return false;
	}

	public boolean isActive() {
		return active;
	}
	

	public String getName() {
		return name;
	}

	public Collection<DTOUser> getUsers() {
		return DTOusers;
	}

	@Override
	public DTOTeam toDTO(Team entity) {
		DTOUser DTOuser = null;
		Collection<User> users = new ArrayList<>(entity.getUsers());
		Collection<DTOUser> DTOusers = new ArrayList<>();
		for(User u : users){
			DTOuser = DTOuser.toDTO(u);
			DTOusers.add(DTOuser);
		}
		return new DTOTeam(entity.getName(), entity.getId(), DTOusers);
	}

	@Override
	public Team toEntity(DTOTeam dataTransferObject) {
		User user = null;
		Collection<User> users = new ArrayList<>();
		Collection<DTOUser> DTOusers = new ArrayList<>(dataTransferObject.getUsers());
		for(DTOUser dtoU : DTOusers){
			user = dtoU.toEntity(dtoU);
			users.add(user);
		}
		Team team =  new Team(dataTransferObject.getName()).setActive(dataTransferObject.isActive());
		for(User u : users){
			team.addUser(u);
		}
		
		return team;
	}
	
}
