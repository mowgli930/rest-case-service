package se.plushogskolan.restcaseservice;

import se.plughogskolan.restcaseservice.model.DTOIssue;
import se.plughogskolan.restcaseservice.model.DTOUser;
import se.plughogskolan.restcaseservice.model.DTOWorkItem;
import se.plushogskolan.casemanagement.model.Issue;
import se.plushogskolan.casemanagement.model.User;
import se.plushogskolan.casemanagement.model.WorkItem;;

public class ModelConverter {
	
	public Issue fromDTOToIssue(DTOIssue issueWeb){
		return new Issue(fromDTOToWorkItem(issueWeb.getWorkitem()), issueWeb.getDescription());
	}
	
	public WorkItem fromDTOToWorkItem(DTOWorkItem workItemWeb){
		WorkItem wi = new WorkItem(workItemWeb.getDescription(), workItemWeb.getStatus());
		if(workItemWeb.getUser != null){
			wi.setUser(workItemWeb.getUser());
		}
		if(workItemWeb.getIssue != null){
			wi.setIssue(workItemWeb.getIssue());
		}
		return wi;
	}
	
	public User fromDTOToUser(DTOUser user){
		User newUser = new User(user.getUserName());
	}

}
