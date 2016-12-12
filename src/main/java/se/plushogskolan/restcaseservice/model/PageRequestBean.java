package se.plushogskolan.restcaseservice.model;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.QueryParam;

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

}
