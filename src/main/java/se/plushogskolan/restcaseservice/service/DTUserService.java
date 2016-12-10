package se.plushogskolan.restcaseservice.service;

import org.springframework.beans.factory.annotation.Autowired;

import se.plushogskolan.casemanagement.exception.AlreadyPersistedException;
import se.plushogskolan.casemanagement.exception.InternalErrorException;
import se.plushogskolan.casemanagement.model.User;
import se.plushogskolan.casemanagement.service.CaseService;
import se.plushogskolan.restcaseservice.exception.ConflictException;
import se.plushogskolan.restcaseservice.exception.WebInternalErrorException;

public class DTUserService {

	@Autowired
	CaseService service;

	public User saveUser(User user) {

		try {
			return service.save(user);
		} catch (AlreadyPersistedException e1) {
			throw new ConflictException("User already exists");
		} catch (InternalErrorException e2) {
			throw new WebInternalErrorException("Server error");
		}

	}

}
