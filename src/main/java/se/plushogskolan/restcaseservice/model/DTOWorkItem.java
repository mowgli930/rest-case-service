package se.plushogskolan.restcaseservice.model;

import se.plushogskolan.casemanagement.model.WorkItem;
import se.plushogskolan.casemanagement.model.WorkItem.Status;

public final class DTOWorkItem extends AbstractDTO {

	private final String description;
	private final Status status;

	public DTOWorkItem(Long id, String description, Status status) {
		super(id);
		this.description = description;
		this.status = status;
	}
	
	private DTOWorkItem() {
		super(null);
		this.description = null;
		this.status = null;
	}

	public String getDescription() {
		return description;
	}
	public Status getStatus() {
		return status;
	}

	public static WorkItemBuilder builder(String description, Status status) {
		return new WorkItemBuilder(description, status);
	}
	
	@Override
	public int hashCode() {
		int result = 17;
		result += 31 * this.getId();
		result += 31 * description.hashCode();
		result += 31 * status.hashCode();
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(this == obj) 
			return true;
		else if(obj instanceof DTOWorkItem) {
			DTOWorkItem other = (DTOWorkItem) obj;
			return this.getId() == other.getId()
					&& description.equals(other.getDescription())
					&& status.equals(other.getStatus());
		}
		else
			return false;
	}
	
	public static DTOWorkItem toDTO(WorkItem entity) {
		return DTOWorkItem.builder(entity.getDescription(), entity.getStatus())
				.setId(entity.getId()).build();
	}

	public static WorkItem toEntity(DTOWorkItem dataTransferObject) {
		return new WorkItem(dataTransferObject.getDescription(), dataTransferObject.getStatus());
	}

	public static final class WorkItemBuilder {
		//Required
		private final String description;
		private final Status status;
		//Optional
		private Long id = null;
		
		private WorkItemBuilder(String description, Status status) {
			this.description = description;
			this.status = status;
		}
		
		public WorkItemBuilder setId(Long id) {
			this.id = id;
			return this;
		}
		
		public DTOWorkItem build() {
			return new DTOWorkItem(this.id, this.description, this.status);
		}
	}
}
