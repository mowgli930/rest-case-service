package se.plughogskolan.restcaseservice.model;

import se.plushogskolan.casemanagement.model.AbstractEntity;

public interface ModelConverter<E extends AbstractEntity, D extends AbstractDTO> {

	D toDTO(E entity);
	
	E toEntity(D dataTransferObject);
	
}
