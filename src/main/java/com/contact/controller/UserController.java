package com.contact.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.contact.Entity.Contact;
import com.contact.Rest.ClientSession;
import com.contact.controller.ex.SystemMalfunctionException;
import com.contact.controller.ex.UserMalformExeption;

@RestController
@RequestMapping("/api")
public class UserController {



	    /* Map with Key and Value For Create Session */
	    private Map<String, ClientSession> tokenMap;
	    /* Get the Token And work with */
	    private String token;
	    public UserController(@Qualifier("tokens") Map<String, ClientSession> tokenMap) {
	        this.tokenMap = tokenMap;
	    }
	    @GetMapping("/{token}/contacts")
	    public ResponseEntity<List<Contact>> getAllContacts(@PathVariable String token) throws SystemMalfunctionException {
	        ClientSession session = tokenMap.get(token);
	       List<Contact> allContacts = session.getUserService().getAllUserContacts();
	        if(allContacts!= null){
	           this.token = token; // For tests
	            session.accessed();
	            return ResponseEntity.ok(allContacts);
	        }
	        throw new SystemMalfunctionException("No Connection to data base");
	    }
	    @PostMapping("/contact")
	    public ResponseEntity<Contact> createContact(@RequestBody Contact contact) throws UserMalformExeption {
	        ClientSession session = tokenMap.get(this.token);
	        Contact contact1 = session.getUserService().createContact(contact);
	        return ResponseEntity.ok(contact1);
	    }
	    @PutMapping("/contact")
	    public ResponseEntity<Contact> updateContact(@RequestBody Contact contact) throws  UserMalformExeption {
	        ClientSession session = tokenMap.get(this.token);
	        Contact updateContact = session.getUserService().updateContact(contact);
	        return ResponseEntity.ok(updateContact);
	    }
	    @GetMapping("/contact/{id}")
	    public ResponseEntity<Contact> getContactById(@PathVariable long id) throws  UserMalformExeption {
	        ClientSession session = tokenMap.get(this.token);
	        Contact contactById = session.getUserService().getContactById(id);
	        if(contactById!= null) {
	            return ResponseEntity.ok(contactById);
	        }
	        throw new  UserMalformExeption("Not Exists");
	    }
	    @DeleteMapping("/contact/{id}")
	    public ResponseEntity<String> deleteContactById(@PathVariable("id") long id) throws  UserMalformExeption {
	        ClientSession session = tokenMap.get(this.token);
	        session.getUserService().removeContactById(id);
	        return ResponseEntity.ok("Contact with id " +id+ " succesfully removed" );
	    }
	}