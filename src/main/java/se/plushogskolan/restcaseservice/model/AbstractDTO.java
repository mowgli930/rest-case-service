package se.plushogskolan.restcaseservice.model;

public abstract class AbstractDTO {
	
	private final Long id;
	
	public AbstractDTO(Long id) {
		this.id = id;
	}
	
	public Long getId() {
		return this.id;
	}	
}
