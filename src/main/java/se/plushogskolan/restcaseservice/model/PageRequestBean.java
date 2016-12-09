package se.plushogskolan.restcaseservice.model;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.QueryParam;

import org.springframework.data.domain.PageRequest;

public class PageRequestBean {

	@QueryParam("page")
	@DefaultValue("0")
	private int page;

	@QueryParam("size")
	@DefaultValue("5")
	private int size;

	public int getPage() {
		return page;
	}

	public int getSize() {
		return size;
	}
	
	public PageRequest getPageRequest(){
		return new PageRequest(page, size);
	}

}
