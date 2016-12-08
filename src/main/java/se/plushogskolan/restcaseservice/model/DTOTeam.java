package se.plushogskolan.restcaseservice.model;

import se.plushogskolan.casemanagement.model.Team;

public class DTOTeam extends AbstractDTO implements ModelConverter<Team, DTOTeam>{

	private String name;

	private boolean active;

	public DTOTeam(Long id, String name, boolean active) {
		super(id);
		this.active = active;
		this.name = name;
	}
	
	public static DTOTeamBuilder builder(String name, boolean active){
		return new DTOTeamBuilder(name, active);
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

	@Override
	public DTOTeam toDTO(Team entity) {
		return DTOTeam.builder(entity.getName(), entity.isActive()).build();
	}

	@Override
	public Team toEntity(DTOTeam dataTransferObject) {
		Team team = new Team(dataTransferObject.getName());
		return team;
	}
	
	public static final class DTOTeamBuilder{
		
		private Long id = null;
		private String name;
		private boolean active = true;
		
		private DTOTeamBuilder(String name, boolean active){
			this.name = name;
			this.active = active;
		}
		
		public DTOTeamBuilder setName(String name){
			this.name = name;
			return this;
		}
		
		public DTOTeamBuilder setActive(boolean active){
			this.active = active;
			return this;
		}
		
		public DTOTeam build(){
			return new DTOTeam(id, name, active);
		}
	}
	
}
