package se.plushogskolan.restcaseservice.model;

import se.plushogskolan.casemanagement.model.User;
import se.plushogskolan.casemanagement.model.WorkItem;
import se.plushogskolan.casemanagement.model.WorkItem.Status;

public class DTOWorkItem extends AbstractDTO implements ModelConverter<WorkItem, DTOWorkItem> {

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

	@SuppressWarnings("null")
	@Override
	public DTOWorkItem toDTO(WorkItem entity) {
		DTOUser dtoUser = null;
		if(entity.getUser() != null) {
			User user = entity.getUser();
			dtoUser = dtoUser.toDTO(user);
		}
		return new DTOWorkItem(entity.getId(), entity.getDescription(), entity.getStatus(), dtoUser);
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

}
