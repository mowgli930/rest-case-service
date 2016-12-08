package se.plushogskolan.restcaseservice.model;

import se.plushogskolan.casemanagement.model.User;

public class DTOUser extends AbstractDTO implements ModelConverter<User, DTOUser>{

	public DTOUser(Long id) {
		super(id);
		// TODO Auto-generated constructor stub
	}

	@Override
	public DTOUser toDTO(User entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User toEntity(DTOUser dataTransferObject) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
