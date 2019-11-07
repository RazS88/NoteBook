package com.contact.Rest;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.contact.Service.UserService;



//regular component that every one use it and for spring to know in Configuration

/* Scope for not being singleton */
@Component
@Scope("prototype")
public class ClientSession {
	
	// Fields

	/* All Service Fields Use */
	
	private UserService service;
	
	private long lastAccessedMillis;
	
	//Getters and Setters
	public UserService getUserService() {
		return service;
	}

	public void setUserService(UserService service) {
		this.service = service;
	}


	
	public long getLastAccessedMillis() {
		return lastAccessedMillis;
	}

	// Change for the current time to Know When Lost Or made An action
	public void accessed() {
		this.lastAccessedMillis =System.currentTimeMillis();
	}
	
	
	

}
