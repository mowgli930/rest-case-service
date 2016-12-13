package se.plushogskolan.restcaseservice.model;

import se.plushogskolan.casemanagement.model.Issue;

public final class DTOIssue extends AbstractDTO implements ModelConverter<Issue, DTOIssue>{

	private final String description;
	private DTOWorkItem dtoWorkItem;

	public DTOIssue(Long id, String description, DTOWorkItem dtoWorkItem) {
		super(id);
		this.description = description;
		this.dtoWorkItem = dtoWorkItem;
	}
	
	public String getDescription() {
		return description;
	}
	
	public DTOWorkItem getDtoWorkItem() {
		return dtoWorkItem;
	}
	
	public static DTOIssueBuilder builder(DTOWorkItem dtoWorkItem, String description){
		return new DTOIssueBuilder(description, dtoWorkItem);
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

	@Override
	public DTOIssue toDTO(Issue entity) {
		return DTOIssue.builder(dtoWorkItem.toDTO(entity.getWorkitem()), 
								entity.getDescription()).setId(entity.getId()).build();
	}

	@Override
	public Issue toEntity(DTOIssue dataTransferObject) {
		Issue issue = new Issue(dtoWorkItem.toEntity(dataTransferObject.getDtoWorkItem()), 
								dataTransferObject.getDescription());
		return issue;
	}
	
	public static final class DTOIssueBuilder{
		
		private Long id = null;
		private String description;
		private DTOWorkItem dtoWorkItem;

		public DTOIssueBuilder(String description, DTOWorkItem dtoWorkItem) {
			this.description = description;
			this.dtoWorkItem = dtoWorkItem;
		}
		
		public DTOIssueBuilder setId(Long id){
			this.id = id;
			return this;
		}
		
		public DTOIssueBuilder setDescription(String description){
			this.description = description;
			return this;
		}
		
		public DTOIssueBuilder setDTOWorkItem(DTOWorkItem dtoWorkItem){
			this.dtoWorkItem = dtoWorkItem;
			return this;
		}
		
		public DTOIssue build(){
			return new DTOIssue(id, description, dtoWorkItem);
		}
		
	}
	
}
