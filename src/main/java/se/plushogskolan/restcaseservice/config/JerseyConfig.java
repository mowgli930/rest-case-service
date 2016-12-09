package se.plushogskolan.restcaseservice.config;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

import se.plushogskolan.restcaseservice.resource.IssueResource;
import se.plushogskolan.restcaseservice.resource.UserResource;
import se.plushogskolan.restcaseservice.resource.WorkItemResource;
import se.plushogskolan.restcaseservice.service.DTOIssueService;

@Component
public class JerseyConfig extends ResourceConfig {

	public JerseyConfig() {
		register(UserResource.class);
		register(WorkItemResource.class);
		register(IssueResource.class);
		register(DTOIssueService.class);
	}

}
