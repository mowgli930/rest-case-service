package se.plughogskolan.model;

public class AbstractEntity {
	
	private final Long id;
	
	public AbstractEntity(Long id) {
		this.id = id;
	}
	
	public Long getId() {
		return this.id;
	}
	
}
