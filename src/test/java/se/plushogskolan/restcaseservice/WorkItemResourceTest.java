package se.plushogskolan.restcaseservice;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertNotNull;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import se.plushogskolan.casemanagement.model.WorkItem;
import se.plushogskolan.casemanagement.repository.WorkItemRepository;
import se.plushogskolan.restcaseservice.config.JerseyConfig;
import se.plushogskolan.restcaseservice.model.DTOWorkItem;

public class WorkItemResourceTest {
	
	private static Client client; //TODO Make sure it can be a static global variable
	private static AnnotationConfigApplicationContext context;
	private static WorkItemRepository workItemRepository;
	
	private static WorkItem dummyWorkItem;
	private final String DOMAIN = "http://127.0.0.1:8080";
	private final String RESOURCE = "workitems";
	
	@BeforeClass
	public static void setUp() {
		client = ClientBuilder.newClient(new JerseyConfig());
		context = new AnnotationConfigApplicationContext();
		context.scan("se.plushogskolan.casemanagement");
		context.refresh();
		workItemRepository = context.getBean(WorkItemRepository.class);
		dummyWorkItem = new WorkItem("This is a test", WorkItem.Status.STARTED);
	}
	
	@Test
	public void canPostWorkItem() {
		DTOWorkItem beanToPost = new DTOWorkItem(101L, "Get some shit done", WorkItem.Status.UNSTARTED);
		
		Response post = newInvocation().post(Entity.json(beanToPost));
		Long id = getIdFromLocation(post.getHeaderString("Location"));
		
		DTOWorkItem postedBean = getPostedDTO(id);
		DTOWorkItem databaseBean = getFromDatabase(id);
		
		assertEquals(postedBean, databaseBean);
		assertEquals(post.getStatus(), 201);
	}
	
	@Test
	public void canGetPostedWorkItem() {
		dummyWorkItem = workItemRepository.save(dummyWorkItem);
		DTOWorkItem expected = DTOWorkItem.toDTO(dummyWorkItem);
		DTOWorkItem actual = newInvocation(dummyWorkItem.getId().toString()).get(DTOWorkItem.class);
				
		assertEquals(expected, actual);
	}
	
	@Test
	public void canDeletePostedWorkItem() {
		dummyWorkItem = workItemRepository.save(dummyWorkItem);
		assertNotNull(workItemRepository.findOne(dummyWorkItem.getId()));
		
		newInvocation(dummyWorkItem.getId().toString()).delete();
		assertNull(workItemRepository.findOne(dummyWorkItem.getId()));
	}
	
	@Test
	public void canUpdateStatusTest() {
		dummyWorkItem.setStatus(WorkItem.Status.UNSTARTED);
		dummyWorkItem = workItemRepository.save(dummyWorkItem);
		DTOWorkItem dto = DTOWorkItem.toDTO(dummyWorkItem);
		newInvocation("83", "status=started").put(Entity.json(dto));
//		assertTrue(true);
	}

//	@Test
	public void DELETEME() {
		dummyWorkItem = workItemRepository.save(dummyWorkItem);
		WebTarget target = client.target(DOMAIN).path(RESOURCE).path(dummyWorkItem.getId().toString());
		Invocation.Builder invocationBuilder = target.request(MediaType.APPLICATION_JSON)
				.header("Authorization", "plz-give-VG");
		DTOWorkItem dto = DTOWorkItem.toDTO(dummyWorkItem);
		invocationBuilder.post(Entity.json(dto));
	}
	
	@AfterClass
	public static void tearDown() {
		workItemRepository.deleteAll();
	}
		
	private Invocation.Builder newInvocation() {
		WebTarget target = client.target(DOMAIN).path(RESOURCE);
		Invocation.Builder invocationBuilder = target.request(MediaType.APPLICATION_JSON)
				.header("Authorization", "plz-give-VG");
		return invocationBuilder;
	}
	private Invocation.Builder newInvocation(String id) {
		WebTarget target = client.target(DOMAIN).path(RESOURCE).path(id);
		Invocation.Builder invocationBuilder = target.request(MediaType.APPLICATION_JSON)
				.header("Authorization", "plz-give-VG");
		return invocationBuilder;
	}
	private Invocation.Builder newInvocation(String id, String ... params) {
		WebTarget target = client.target(DOMAIN).path(RESOURCE).path(id);
		for(String s: params) {
			String[] split = s.split("=");
			System.err.println(split[0] + split[1]);
			target.queryParam(split[0], split[1]);
		}
		Invocation.Builder invocationBuilder = target.request(MediaType.APPLICATION_JSON)
				.header("Authorization", "plz-give-VG");
		return invocationBuilder;
	}
	
	private Long getIdFromLocation(String location) {
		String[] split = location.split("/");
		Long id = Long.parseLong(split[4]);
		return id;
	}
	
	private DTOWorkItem getPostedDTO(Long id) {
		Response get = newInvocation(id.toString()).get();
		return get.readEntity(DTOWorkItem.class);
	}
	
	private DTOWorkItem getFromDatabase(Long id) {
		WorkItem workItem = workItemRepository.findOne(id);
		return DTOWorkItem.toDTO(workItem);
	}
}
