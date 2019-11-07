package com.contact.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.contact.Entity.Contact;
import com.contact.Entity.User;
import com.contact.Repository.ContactRepo;
import com.contact.Repository.UserRepo;


@Service
@Scope("prototype")
public class UserServiceImpl implements UserService {

	private long userId;

	private UserRepo userDao;

	private ContactRepo contactDao;
	

	@Autowired
	public UserServiceImpl(ContactRepo contactDao, UserRepo userDao ) {
		this.contactDao = contactDao;
		this.userDao = userDao;

	}

	@Override
	public Contact createContact(Contact contact) {
		User userById = getUserById();
		contact.setId(0);
		userById.addContacts(contact);
		return contactDao.save(contact);
	}

	@Override
	public Contact getContactById(long id) {
		return contactDao.findById(id).orElse(null);
	}

	@Override
	public void removeContactById(long id) {
		contactDao.deleteById(id);

	}

	@Override
	public Contact updateContact(Contact contact) {
		return contactDao.save(contact);
	}

	@Override
	public List<Contact> getAllUserContacts() {
		return contactDao.findAllByUserId(this.userId);
	}

	private User getUserById() {
		return userDao.findById(this.userId).orElse(null);
	}

	public void setId(long id) {
		this.userId = id;
	}






	
}
