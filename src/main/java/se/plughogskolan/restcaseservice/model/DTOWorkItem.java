package se.plughogskolan.restcaseservice.model;

import se.plushogskolan.casemanagement.model.User;
import se.plushogskolan.casemanagement.model.WorkItem;
import se.plushogskolan.casemanagement.model.WorkItem.Status;

public final class DTOWorkItem extends AbstractDTO implements ModelConverter<WorkItem, DTOWorkItem> {

	private final String description;
	private final Status status;
	private final DTOUser user;

	public DTOWorkItem(Long id, String description, Status status, DTOUser user) {
		super(id);
		this.description = description;
		this.status = status;
		this.user = user;
	}

	public String getDescription() {
		return description;
	}
	public Status getStatus() {
		return status;
	}
	public DTOUser getUser() {
		return user;
	}

	public static WorkItemBuilder builder(String description, Status status) {
		return new WorkItemBuilder(description, status);
	}
	
	@SuppressWarnings("null")
	@Override
	public DTOWorkItem toDTO(WorkItem entity) {
		DTOUser dtoUser = null;
		if(entity.getUser() != null) {
			User user = entity.getUser();
			dtoUser = dtoUser.toDTO(user);
		}
		return DTOWorkItem.builder(entity.getDescription(), entity.getStatus())
				.setId(entity.getId()).setUser(dtoUser).build();
	}

	@Override
	public WorkItem toEntity(DTOWorkItem dataTransferObject) {
		User user = null;
		if(dataTransferObject.getUser() != null) {
			DTOUser dtoUser = dataTransferObject.getUser();
			user = dtoUser.toEntity(dtoUser);
		}
		return new WorkItem(dataTransferObject.getDescription(), dataTransferObject.getStatus())
				.setUser(user);
	}

	public static final class WorkItemBuilder {
		//Required
		private final String description;
		private final Status status;
		//Optional
		private Long id = null;
		private DTOUser user = null;
		
		private WorkItemBuilder(String description, Status status) {
			this.description = description;
			this.status = status;
		}
		
		public WorkItemBuilder setId(Long id) {
			this.id = id;
			return this;
		}
		public WorkItemBuilder setUser(DTOUser user) {
			this.user = user;
			return this;
		}
		public DTOWorkItem build() {
			return new DTOWorkItem(this.id, this.description, this.status, this.user);
		}
	}
}
