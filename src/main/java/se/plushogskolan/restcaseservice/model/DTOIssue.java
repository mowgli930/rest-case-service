package se.plushogskolan.restcaseservice.model;

import se.plushogskolan.casemanagement.model.Issue;
import se.plushogskolan.casemanagement.model.WorkItem;

public final class DTOIssue extends AbstractDTO implements ModelConverter<Issue, DTOIssue>{

	private final DTOWorkItem workItem;

	private final String description;

	public DTOIssue(DTOWorkItem workItem, String description, Long id) {
		super(id);
		this.workItem = workItem;
		this.description = description;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj instanceof DTOIssue) {
			DTOIssue otherIssue = (DTOIssue) obj;
			return description.equals(otherIssue.description);
		}
		return false;
	}

	@Override
	public int hashCode() {
		int result = 17;
		result += 31 * getId();
		result += 31 * description.hashCode();
		return result;
	}

	public DTOWorkItem getWorkitem() {
		return workItem;
	}

	public String getDescription() {
		return description;
	}

	@SuppressWarnings("null")
	@Override
	public DTOIssue toDTO(Issue entity) {
		DTOWorkItem dtoWorkItem = null;
		if(entity.getWorkitem() != null){
			WorkItem wi = entity.getWorkitem();
			dtoWorkItem = dtoWorkItem.toDTO(wi);
		}
		return new DTOIssue(dtoWorkItem, entity.getDescription(), entity.getId());
	}

	@Override
	public Issue toEntity(DTOIssue dataTransferObject) {
		WorkItem wi = null;
	if(dataTransferObject.getWorkitem() != null){
		DTOWorkItem dtoWorkItem = dataTransferObject.getWorkitem();
		wi = dtoWorkItem.toEntity(dtoWorkItem);
	}
		
		return new Issue(wi, dataTransferObject.getDescription());
	}
	
}
