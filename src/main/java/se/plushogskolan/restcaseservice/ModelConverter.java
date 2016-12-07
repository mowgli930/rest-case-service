package se.plushogskolan.restcaseservice;

import se.plughogskolan.restcaseservice.model.DTOIssue;
import se.plughogskolan.restcaseservice.model.DTOWorkItem;
import se.plushogskolan.casemanagement.model.Issue;
import se.plushogskolan.casemanagement.model.User;
import se.plushogskolan.casemanagement.model.WorkItem;;

public class ModelConverter {
	
	public Issue fromDTOToIssue(DTOIssue issueweb){
		Issue issue = new Issue(fromDTOToWorkItem(issueweb.getWorkitem()), issueweb.getDescription());
	}
	
	public WorkItem fromDTOToWorkItem(DTOWorkItem workItem){
		WorkItem wi = new WorkItem(workItem.getDescription(), workItem.getStatus());
		if(workItem.getUser != null){
			wi.setUser(workItem.getUser());
		}
		if(workItem.getIssue != null){
			wi.setIssue(workItem.getIssue());
		}
		return wi;
	}
	
	public User fromDTOToUser(DTOUser user){
		User newUser = new User(user.getUserName());
	}

}
