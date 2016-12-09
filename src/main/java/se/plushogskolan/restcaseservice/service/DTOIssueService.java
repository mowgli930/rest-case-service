package se.plushogskolan.restcaseservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import se.plushogskolan.casemanagement.model.Issue;
import se.plushogskolan.casemanagement.service.CaseService;
import se.plushogskolan.restcaseservice.model.DTOIssue;

@Component
public class DTOIssueService {
	
	@Autowired
	CaseService service;

	public DTOIssue save(DTOIssue dtoIssue){
		
		Issue issue = dtoIssue.toEntity(dtoIssue);
		issue = service.save(issue);
		return dtoIssue = dtoIssue.toDTO(issue);
	}
	
}
