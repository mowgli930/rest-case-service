package se.plushogskolan.restcaseservice.service;

import java.util.ArrayList;
import java.util.List;

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

	public User updateUserLastName(Long userId, String lastName) {
		try {
			return service.updateUserLastName(userId, lastName);
		} catch (NotPersistedException e1) {
			throw new NotFoundException("User does not exist");
		} catch (InternalErrorException e) {
			throw new WebInternalErrorException("Server error");
		}
	}

	public User updateUserUsername(Long userId, String username) {
		try {
			return service.updateUserUsername(userId, username);
		} catch (IllegalArgumentException e1) {
			throw new BadRequestException("Username is not long enough");
		} catch (NotPersistedException e2) {
			throw new NotFoundException("User does not exist");
		} catch (InternalErrorException e3) {
			throw new WebInternalErrorException("Server error");
		} catch (AlreadyPersistedException e4) {
			throw new ConflictException("Username already exists");
		}
	}

	public User updateUserIsActive(Long userId, boolean isActive) {
		try {
			if (isActive) {
				return service.activateUser(userId);
			} else {
				return service.inactivateUser(userId);
			}

		} catch (NotPersistedException e1) {
			throw new NotFoundException("User does not exist");
		} catch (InternalErrorException e2) {
			throw new WebInternalErrorException("Server error");
		}
	}

	public DTOUser getUser(Long userId) {

		try {
			return DTOUser.builder().build("").toDTO(service.getUser(userId));
		} catch (NotPersistedException e1) {
			throw new NotFoundException("User does not exist");
		}
	}

	public List<DTOUser> searchUsersByFirstNameLastNameUsername(String firstName, String lastName, String username,
			int page, int size) {
		try {
			List<User> list = service.searchUsersByFirstNameLastNameUsername(firstName, lastName, username, page, size);

			return userListToDTOUserList(list);
		} catch (InternalErrorException e1) {
			throw new WebInternalErrorException("Server error");
		}
	}

	public List<DTOUser> getUsersByTeam(Long teamId, int page, int size) {

		try {
			List<User> list = service.getUsersByTeam(teamId, page, size);

			return userListToDTOUserList(list);
		} catch (InternalErrorException e) {
			throw new WebInternalErrorException("Server error");
		}
	}

	private List<DTOUser> userListToDTOUserList(List<User> list) {

		List<DTOUser> listDto = new ArrayList<>();

		DTOUser dtoUser = DTOUser.builder().build("");

		for (User user : list) {
			listDto.add(dtoUser.toDTO(user));
		}

		return listDto;
	}
}
