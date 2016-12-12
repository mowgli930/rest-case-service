package se.plushogskolan.restcaseservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import se.plushogskolan.casemanagement.exception.AlreadyPersistedException;
import se.plushogskolan.casemanagement.exception.InternalErrorException;
import se.plushogskolan.casemanagement.model.Issue;
import se.plushogskolan.casemanagement.service.CaseService;
import se.plushogskolan.restcaseservice.exception.ConflictException;
import se.plushogskolan.restcaseservice.exception.WebInternalErrorException;
import se.plushogskolan.restcaseservice.model.DTOIssue;

@Component
public class DTOIssueService {
	
	private final CaseService service;
	
	@Autowired
	public DTOIssueService(CaseService service){
		this.service = service;
	}
	
	//TODO Add a way to get the workitem and then add it to the issue, lägg till catch om workitem inte hittas
	public Issue save(DTOIssue dtoIssue, Long dtoWorkItemId){
		try{
			Issue issue = dtoIssue.toEntity(dtoIssue);
			return service.save(issue);
		}catch(AlreadyPersistedException e1) {
			throw new ConflictException("Issue already exists");
		}catch(InternalErrorException e2) {
			throw new WebInternalErrorException("Server error");
		}
		
	}
	
	public Issue updateDescription(Long issueId, String description){
		try{
		return service.updateIssueDescription(issueId, description);
		}catch(InternalErrorException e){
			throw new WebInternalErrorException("Server error");
		}
	}
	
	public Issue getIssue(Long dtoIssueId){
		try{
			return service.getIssue(dtoIssueId);
		}catch(InternalErrorException e){
			throw new WebInternalErrorException("Server error");
		}
	}
	
	public List<Issue> getAllIssues(int page, int size){
		try{
		return service.getAllIssues(page, size);
		}catch(InternalErrorException e){
			throw new WebInternalErrorException("Server error");
		}
	}
	
}
