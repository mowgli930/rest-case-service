package se.plughogskolan.model;

import se.plushogskolan.casemanagement.model.WorkItem.Status;

public class DTOWorkItem extends AbstractEntity {
	
	private final String description;
	private final Status status;
	private final DTOUser user;
	
	public DTOWorkItem(Long id, String description, Status status, DTOUser user) {
		super(id);
		this.description = description;
		this.status = status;
		this.user = user;
	}
	
}
