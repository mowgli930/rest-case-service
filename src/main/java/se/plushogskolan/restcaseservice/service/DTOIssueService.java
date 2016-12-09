package se.plushogskolan.restcaseservice.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import se.plushogskolan.casemanagement.model.Issue;
import se.plushogskolan.casemanagement.service.CaseService;
import se.plushogskolan.restcaseservice.model.DTOIssue;
import se.plushogskolan.restcaseservice.model.DTOWorkItem;
import se.plushogskolan.casemanagement.model.WorkItem.Status;

@Component
public class DTOIssueService {
	
	@Autowired
	CaseService service;
	
	//TODO Add a way to get the workitem and then add it to the issue
	public DTOIssue save(DTOIssue dtoIssue, Long dtoWorkItemId){
		
		Issue issue = dtoIssue.toEntity(dtoIssue);
		issue = service.save(issue);
		return dtoIssue = dtoIssue.toDTO(issue);
	}
	
	public DTOIssue updateDescription(Long issueId, String description){
		DTOIssue dtoIssue = new DTOIssue(-1L, "", new DTOWorkItem(-1L, "", Status.DONE));
		Issue issue = service.updateIssueDescription(issueId, description);
		return dtoIssue.toDTO(issue);
	}
	
	public DTOIssue getIssue(Long dtoIssueId){
		DTOIssue dtoIssue = new DTOIssue(-1L, "", new DTOWorkItem(-1L, "", Status.DONE));
		Issue issue = service.getIssue(dtoIssueId);
		return dtoIssue.toDTO(issue);
	}
	
	public List<DTOIssue> getAllIssues(int page, int size){
		DTOIssue dtoIssue = new DTOIssue(-1L, "", new DTOWorkItem(-1L, "", Status.DONE));
		List<DTOIssue> dtoIssues = new ArrayList<>();
		List<Issue> issues = service.getAllIssues(page, size);
		for(Issue i : issues){
			dtoIssues.add(dtoIssue.toDTO(i));
		}
		return dtoIssues;
	}
	
}
