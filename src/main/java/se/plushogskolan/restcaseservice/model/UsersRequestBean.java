package se.plushogskolan.restcaseservice.model;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.QueryParam;

public class UsersRequestBean {
	
	@QueryParam("firstName")
	@DefaultValue("")
	private String firstName;
	
	@QueryParam("lastName")
	@DefaultValue("")
	private String lastName;
	
	@QueryParam("username")
	@DefaultValue("")
	private String username;
	
	@QueryParam("page")
	@DefaultValue("0")
	int page;
	
	@QueryParam("size")
	@DefaultValue("10")
	int size;
	
	public String getFirstName() {
		return firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public String getUsername() {
		return username;
	}

	public int getPage() {
		return page;
	}
	
	public int getSize() {
		return size;
	}
}
