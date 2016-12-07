package se.plughogskolan.restcaseservice.model;

import java.util.Collection;

public class DTOTeam extends AbstractEntity{

	private String name;

	private boolean active;

	private Collection<DTOUser> users;

	public DTOTeam(String name, Long id) {
		super(id);
		this.active = true;
		this.name = name;
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
		return users;
	}
	
}
