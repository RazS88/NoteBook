package com.contact.Service;

import java.util.List;

import com.contact.Entity.Contact;

public interface UserService  {

	//Contacts
	Contact createContact(Contact contact);
	
	Contact getContactById(long id);
	
	void removeContactById(long id);
	
	Contact updateContact(Contact contact);
	
	List<Contact> getAllUserContacts();
	
	void setId(long id);


	



}
