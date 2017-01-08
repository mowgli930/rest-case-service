package se.plushogskolan.restcaseservice;

import static org.junit.Assert.assertTrue;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.junit.BeforeClass;
import org.junit.Test;

import se.plushogskolan.casemanagement.model.WorkItem;
import se.plushogskolan.restcaseservice.config.JerseyConfig;
import se.plushogskolan.restcaseservice.model.DTOWorkItem;

public class WorkItemResourceTest {
	
	static WebTarget target;
	static Client client;
	static Invocation.Builder invocationBuilder;
	
	@BeforeClass
	public static void setUp() {
		client = ClientBuilder.newClient(new JerseyConfig());
		target = client.target("http://127.0.0.1:8080").path("workitems");
		invocationBuilder = target.request(MediaType.APPLICATION_JSON)
				.header("Authorization", "plz-give-VG");
	}
	
//	@Test
	public void getTest() {
		String entity = invocationBuilder.get(String.class);
		
		System.out.println(entity);
		
		assertTrue(true);
	}
	
//	@Test
	public void postTest() {
		DTOWorkItem beanToPost = new DTOWorkItem(101L, "Get some shit done", WorkItem.Status.UNSTARTED);
		
		invocationBuilder.post(Entity.entity(beanToPost, MediaType.APPLICATION_JSON));
		
		assertTrue(true);
	}
	
	@Test
	public void deleteTest() {
		//Must be different targets and stuff
		WebTarget deleteTarget = client.target("http://127.0.0.1:8080").path("workitems/12");
		Invocation.Builder deleteBuilder = deleteTarget.request().header("Authorization", "plz-give-VG");
		
		deleteBuilder.delete();
		
		assertTrue(true);
	}
	
	//TODO create getNewTarget method or something like that
}
