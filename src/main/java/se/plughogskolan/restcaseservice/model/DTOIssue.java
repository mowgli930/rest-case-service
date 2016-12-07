package se.plughogskolan.restcaseservice.model;

public final class DTOIssue extends AbstractEntity{

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
	
}
