package se.plushogskolan.restcaseservice.resource;

import javax.ws.rs.Path;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import se.plushogskolan.casemanagement.service.CaseService;

@Component
@Path("workitems")
public class WorkItemResource {

	@Autowired
	private CaseService service;
}
