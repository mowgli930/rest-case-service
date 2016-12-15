package se.plushogskolan.restcaseservice.service;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.NotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import se.plushogskolan.casemanagement.exception.AlreadyPersistedException;
import se.plushogskolan.casemanagement.exception.InternalErrorException;
import se.plushogskolan.casemanagement.exception.NotPersistedException;
import se.plushogskolan.casemanagement.model.Issue;
import se.plushogskolan.casemanagement.model.WorkItem;
import se.plushogskolan.casemanagement.service.CaseService;
import se.plushogskolan.restcaseservice.exception.ConflictException;
import se.plushogskolan.restcaseservice.exception.WebInternalErrorException;
import se.plushogskolan.restcaseservice.model.DTOIssue;

@Component
public class DTOIssueService {

	private final CaseService service;

	@Autowired
	public DTOIssueService(CaseService service) {
		this.service = service;
	}

	public Issue save(DTOIssue dtoIssue, WorkItem workItem) {
		try {
			Issue issue = dtoIssue.toEntity(dtoIssue);
			issue.setWorkItem(workItem);
			return service.save(issue);
		} catch (AlreadyPersistedException e1) {
			throw new ConflictException("Issue already exists");
		} catch (InternalErrorException e2) {
			throw new WebInternalErrorException("Server error");
		}

	}

	public Issue updateDescription(Long issueId, String description) {
		try {
			return service.updateIssueDescription(issueId, description);
		}catch (NotPersistedException e1) {
			throw new NotFoundException("User does not exist");
		} catch (InternalErrorException e) {
			throw new WebInternalErrorException("Server error");
		}
	}

	public DTOIssue getIssue(Long dtoIssueId) {
		try {
			return DTOIssue.builder(null, "").build().toDTO(service.getIssue(dtoIssueId));
		} catch (NotPersistedException e1) {
			throw new NotFoundException("Issue does not exist");
		} catch (InternalErrorException e) {
			throw new WebInternalErrorException("Server error");
		}
	}

	public List<DTOIssue> getAllIssues(int page, int size) {
		try {
			DTOIssue dtoIssue = DTOIssue.builder(null, "").build();
			List<Issue> issues = service.getAllIssues(page, size);
			List<DTOIssue> dtoIssues = new ArrayList<>();
			for (Issue i : issues) {
				dtoIssues.add(dtoIssue.toDTO(i));
			}
			return dtoIssues;
		} catch (InternalErrorException e) {
			throw new WebInternalErrorException("Server error");
		}
	}

}
