package se.plushogskolan.restcaseservice.model;

import se.plushogskolan.casemanagement.model.User;

public final class DTOUser extends AbstractDTO {

	private final String firstName;
	private final String lastName;
	private final String username;
	private final Boolean active;

	private DTOUser(Long id, String firstName, String lastName, String username, Boolean isActive) {
		super(id);
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.active = isActive;
	}

	private DTOUser() {
		super(null);
		this.username = null;
		this.firstName = null;
		this.lastName = null;
		this.active = null;
	}

	public static DTOUserBuilder builder() {
		return new DTOUserBuilder();
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getUsername() {
		return username;
	}

	public Boolean isActive() {
		return active;
	}

	@Override
	public boolean equals(Object obj) {
		if(this == obj)
			return true;
		else if(obj instanceof DTOUser) {
			DTOUser other = (DTOUser) obj;
			return firstName.equals(other.getFirstName()) && lastName.equals(other.getLastName())
					&& username.equals(other.getUsername()) && active == other.isActive();
		}
		return false;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}

	public static DTOUser toDTO(User entity) {

		DTOUserBuilder builder = new DTOUserBuilder();
		builder.setFirstName(entity.getFirstName()).setLastName(entity.getLastName()).setIsActive(entity.isActive())
				.setId(entity.getId());

		return builder.build(entity.getUsername());
	}

	public static User toEntity(DTOUser dataTransferObject) {

		User user = new User(dataTransferObject.getUsername()).setFirstName(dataTransferObject.getFirstName())
				.setLastName(dataTransferObject.getLastName()).setActive(dataTransferObject.active);

		return user;
	}

	public static final class DTOUserBuilder {

		private Long id = null;
		private String firstName = "";
		private String lastName = "";
		private Boolean isActive = true;

		private DTOUserBuilder() {
			;
		}

		public DTOUserBuilder setId(Long id) {
			this.id = id;
			return this;
		}

		public DTOUserBuilder setFirstName(String firstName) {
			this.firstName = firstName;
			return this;
		}

		public DTOUserBuilder setLastName(String lastName) {
			this.lastName = lastName;
			return this;
		}

		public DTOUserBuilder setIsActive(Boolean isActive) {
			this.isActive = isActive;
			return this;
		}

		public DTOUser build(String username) {
			return new DTOUser(id, firstName, lastName, username, isActive);
		}

	}

}
