package se.plushogskolan.restcaseservice.model;

import javax.ws.rs.QueryParam;
//TODO finalize everything
public final class WorkItemRequestBean {

	@QueryParam("status")
	private String status;
	
	@QueryParam("teamId")
	private Long teamId;
	
	@QueryParam("userId")
	private Long userId;
	
	@QueryParam("description")
	private String description;
	
	@QueryParam("withissue")
	private boolean withIssue;

	public String getStatus() {
		return status;
	}

	public Long getTeamId() {
		return teamId;
	}

	public Long getUserId() {
		return userId;
	}

	public String getDescription() {
		return description;
	}

	public boolean isWithIssue() {
		return withIssue;
	}

}
