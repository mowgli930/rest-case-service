package se.plushogskolan.restcaseservice.model;

import se.plushogskolan.casemanagement.model.Team;

public final class DTOTeam extends AbstractDTO{

	private final String name;
	private final Boolean isActive;

	public DTOTeam(Long id, String name, Boolean active) {
		super(id);
		this.isActive = active;
		this.name = name;
	}

	private DTOTeam() {
		super(null);
		name = null;
		isActive = null;
	}

	public static DTOTeamBuilder builder(String name, boolean active) {
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
			return name.equals(otherTeam.getName()) && isActive == otherTeam.getIsActive();
		}
		return false;
	}
	
	public Boolean getIsActive() {
		return isActive;
	}

	public String getName() {
		return name;
	}

	public static DTOTeam toDTO(Team entity) {
		return DTOTeam.builder(entity.getName(), entity.isActive()).setId(entity.getId()).build();
	}

	public static Team toEntity(DTOTeam dataTransferObject) {
		Team team = new Team(dataTransferObject.getName());
		team.setActive(dataTransferObject.getIsActive());
		return team;
	}

	public static final class DTOTeamBuilder {

		private Long id = null;
		private String name;
		private Boolean active = true;

		private DTOTeamBuilder(String name, Boolean active) {
			this.name = name;
			this.active = active;
		}
		
		public DTOTeamBuilder setId(Long id){
			this.id = id;
			return this;
		}

		public DTOTeamBuilder setName(String name) {
			this.name = name;
			return this;
		}

		public DTOTeamBuilder setActive(Boolean active) {
			this.active = active;
			return this;
		}

		public DTOTeam build() {
			return new DTOTeam(id, name, active);
		}
	}

}
