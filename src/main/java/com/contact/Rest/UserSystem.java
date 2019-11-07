package com.contact.Rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.contact.Entity.User;
import com.contact.Repository.UserRepo;
import com.contact.Rest.ex.InvalidLoginExeption;
import com.contact.Service.UserService;

/*Help Spring to know that this is service */
@Service
public class UserSystem {

	// Fields

	/* All Service Fields Use */
	private ClientSession session;

	private UserService userService;

	private UserRepo userDao;

	// Make a DI

	// Constructor initializing ServiceFields
	@Autowired
	public UserSystem(ClientSession session, UserService userService, UserRepo userDao) {
		this.session = session;
		this.userService = userService;
		this.userDao = userDao;

	}

	/**
	 * Get email and Password Cake the type of Customer Get the right Service for
	 * User Type
	 */
	public ClientSession login(String email, String password) throws InvalidLoginExeption {
//		String password1 = "1234";
//
//		String email1 = "admin";
//
//		if (email.equals(email1) && password.equals(password1)) {
//			session.setAdminService(adminService);
//
//			session.accessed();
//			return session;
//		}

		User user = userDao.findByEmailAndPassword(email, password);

		if (user == null) {
			throw new InvalidLoginExeption("Email or password are invalid");
		} else {
			long id = user.getId();
			userService.setId(id);
			session.setUserService(userService);
			
	
			session.accessed();
			return session;
		}

	}

}
