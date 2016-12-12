package se.plushogskolan.restcaseservice.model;

import javax.ws.rs.QueryParam;
//TODO finalize everything
public class WorkItemRequestBean {

	@QueryParam("status")
	String status;
	
	@QueryParam("teamId")
	Long teamId;
	
	@QueryParam("userId")
	Long userId;
	
	@QueryParam("description")
	String description;
	
	@QueryParam("withissue")
	boolean withIssue;

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
