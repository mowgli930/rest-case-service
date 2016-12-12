package se.plushogskolan.restcaseservice.model;

import se.plushogskolan.casemanagement.model.User;

public final class DTOUser extends AbstractDTO implements ModelConverter<User, DTOUser> {

	private final String firstName;
	private final String lastName;
	private final String username;
	private final boolean isActive;

	private DTOUser(Long id, String firstName, String lastName, String username, boolean isActive) {
		super(id);
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.isActive = isActive;
	}
	
	private DTOUser(){
		super(null);
		this.username = "";
		this.firstName = "";
		this.lastName = "";
		this.isActive = true;
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

	public boolean isActive() {
		return isActive;
	}

	@Override
	public boolean equals(Object other) {

		if (this == other) {
			return true;
		}

		if (null == other) {
			return false;
		}

		if (other instanceof User) {
			User otherUser = (User) other;
			return firstName.equals(otherUser.getFirstName()) && lastName.equals(otherUser.getLastName())
					&& username.equals(otherUser.getUsername()) && isActive == otherUser.isActive();
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

	@Override
	public DTOUser toDTO(User entity) {
		
		DTOUserBuilder builder =  new DTOUserBuilder();
		builder.setFirstName(entity.getFirstName())
		.setLastName(entity.getLastName())
		.setActive(entity.isActive())
		.setId(entity.getId());
	
		return builder.build(entity.getUsername());
	}

	@Override
	public User toEntity(DTOUser dataTransferObject) {

		User user = new User(dataTransferObject.getUsername())
		.setFirstName(dataTransferObject.getFirstName())
		.setLastName(dataTransferObject.getLastName())
		.setActive(dataTransferObject.isActive);
		
		return user;
	}

	public static final class DTOUserBuilder {

		private Long id = null;
		private String firstName = "";
		private String lastName = "";
		private boolean isActive = true;
	
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

		public DTOUserBuilder setActive(boolean isActive) {
			this.isActive = isActive;
			return this;
		}

		public DTOUser build(String username) {
			return new DTOUser(id, firstName, lastName, username, isActive);
		}

	}

}
