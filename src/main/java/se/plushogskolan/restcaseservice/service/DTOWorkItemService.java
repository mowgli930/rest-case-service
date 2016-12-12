package se.plushogskolan.restcaseservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import se.plushogskolan.casemanagement.model.WorkItem;
import se.plushogskolan.casemanagement.model.WorkItem.Status;
import se.plushogskolan.casemanagement.service.CaseService;
import se.plushogskolan.restcaseservice.model.DTOWorkItem;

@Component
public class DTOWorkItemService {

	private final CaseService service;
	
	@Autowired
	public DTOWorkItemService(CaseService service){
		this.service = service;
	}
	
	public WorkItem save(DTOWorkItem dtoWorkItem) {
		WorkItem workItem = dtoWorkItem.toEntity(dtoWorkItem);
		workItem = service.save(workItem);
		return workItem;
	}

	public WorkItem updateStatusById(Long workItemId, String status) {
		Status workItemStatus = stringToStatus(status);
		return service.updateStatusById(workItemId, workItemStatus);
	}

	public void deleteWorkItem(Long workItemId) {
		service.deleteWorkItem(workItemId);
		return;
	}

	public WorkItem addWorkItemToUser(Long workItemId, Long userId) {
		return null;
	}

	public List<WorkItem> searchWorkItemByDescription(String description, int page, int size) {
		return service.searchWorkItemByDescription(description, page, size);
	}

	public List<WorkItem> getWorkItemsByStatus(String status, int page, int size) {
		Status workItemStatus = stringToStatus(status);
		return service.getWorkItemsByStatus(workItemStatus, page, size);
	}

	public List<WorkItem> getWorkItemsByTeamId(Long teamId, int page, int size) {
		return service.getWorkItemsByTeamId(teamId, page, size);
	}

	public List<WorkItem> getWorkItemsByUserId(Long userId, int page, int size) {
		return service.getWorkItemsByUserId(userId, page, size);
	}

	public List<WorkItem> getWorkItemsWithIssue(int page, int size) {
		return service.getWorkItemsWithIssue(page, size);
	}
	
	public List<WorkItem> getAllWorkItems(int page, int size) {
		return service.getAllWorkItems(page, size);
	}
	
	//TODO maybe implement, CaseService uses Date instead of LocalDate
//	public List<WorkItem> getWorkItemsByPeriodAndStatus(String status, LocalDate fromDate, 
//			LocalDate toDate, int page, int size) {
//		return service.getWorkItemsByPeriodAndStatus(stringToStatus(status), fromDate, toDate, page, size);
//	}
	
	private WorkItem.Status stringToStatus(String value) {
		WorkItem.Status status = null;
		
		if(value.equals("done") || value.equals("DONE"))
			status = WorkItem.Status.DONE;
		else if(value.equals("unstarted") || value.equals("UNSTARTED"))
			status = WorkItem.Status.UNSTARTED;
		else if(value.equals("started") || value.equals("STARTED"))
			status = WorkItem.Status.STARTED;
		
		return status;
	}
}
