package se.plushogskolan.restcaseservice.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Component;

import se.plushogskolan.casemanagement.model.WorkItem;
import se.plushogskolan.casemanagement.service.CaseService;
import se.plushogskolan.restcaseservice.model.DTOWorkItem;

@Component
public class DTOWorkItemService {

	@Autowired
	CaseService service;
	
	public DTOWorkItem save(DTOWorkItem DTOWorkItem) {
		return null;
	}

	public DTOWorkItem updateStatusById(Long DTOWorkItemId, WorkItem.Status DTOWorkItemStatus) {
		return null;
	}

	public void deleteDTOWorkItem(Long DTOWorkItemId) {
		return;
	}

	public DTOWorkItem addDTOWorkItemToUser(Long DTOWorkItemId, Long userId) {
		return null;
	}

	public Slice<DTOWorkItem> searchDTOWorkItemByDescription(String description, Pageable pageable) {
		return null;
	}

	public Slice<DTOWorkItem> getDTOWorkItemsByStatus(WorkItem.Status DTOWorkItemStatus, Pageable pageable) {
		return null;
	}

	public Slice<DTOWorkItem> getDTOWorkItemsByTeamId(Long teamId, Pageable pageable) {
		return null;
	}

	public Slice<DTOWorkItem> getDTOWorkItemsByUserId(Long userId, Pageable pageable) {
		return null;
	}

	public Slice<DTOWorkItem> getDTOWorkItemsWithIssue(Pageable pageable) {
		return null;
	}
	
	public Page<DTOWorkItem> getAllDTOWorkItems(Pageable pageable) {
		return null;
	}
	
	public List<DTOWorkItem> getAllDoneDTOWorkItemsBetween(LocalDate fromDate, LocalDate toDate) {
		return null;
	}
}
