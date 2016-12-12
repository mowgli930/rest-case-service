package se.plushogskolan.restcaseservice.service;

import static org.mockito.Mockito.calls;

import javax.ws.rs.BadRequestException;
import javax.ws.rs.NotFoundException;

import org.springframework.stereotype.Component;

import se.plushogskolan.casemanagement.exception.AlreadyPersistedException;
import se.plushogskolan.casemanagement.exception.InternalErrorException;
import se.plushogskolan.casemanagement.exception.NotPersistedException;
import se.plushogskolan.casemanagement.model.User;
import se.plushogskolan.casemanagement.service.CaseService;
import se.plushogskolan.restcaseservice.exception.ConflictException;
import se.plushogskolan.restcaseservice.exception.WebInternalErrorException;
import se.plushogskolan.restcaseservice.model.DTOUser;

@Component
public class DTOUserService {

	private final CaseService service;

	public DTOUserService(CaseService service) {
		this.service = service;
	}

	public User saveUser(DTOUser dtoUser) {
		User savedUser = dtoUser.toEntity(dtoUser);
		try {
			return service.save(savedUser);
		} catch (AlreadyPersistedException e1) {
			throw new ConflictException("Username already exists");
		} catch (InternalErrorException e2) {
			throw new WebInternalErrorException("Server error");
		}
	}

	public User updateUserFirstName(Long userId, String firstName) {
		try {
			return service.updateUserFirstName(userId, firstName);
		} catch (NotPersistedException e1) {
			throw new NotFoundException("User does not exist");
		} catch (InternalErrorException e2) {
			throw new WebInternalErrorException("Server error");
		}
	}
	
	public User updateUserLastName(Long userId, String lastName){
		try{
			return service.updateUserLastName(userId, lastName);
		}catch (NotPersistedException e1) {
			throw new NotFoundException("User does not exist");
		}catch (InternalErrorException e) {
			throw new WebInternalErrorException("Server error");
		}
	}
	
	public User updateUserUsername(Long userId, String username){
		try{
			return service.updateUserUsername(userId, username);
		}catch (IllegalArgumentException e1) {
			throw new BadRequestException("Username is not long enough");
		}catch (NotPersistedException e2) {
			throw new NotFoundException("User does not exist");
		}catch (InternalErrorException e3) {
			throw new WebInternalErrorException("Server error");
		}
	}

}
