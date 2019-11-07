package com.contact.controller;

import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.contact.Entity.Token;
import com.contact.Rest.ClientSession;
import com.contact.Rest.UserSystem;
import com.contact.Rest.ex.InvalidLoginExeption;

/*Help Spring to know that this is Controller Rest 
*works with Client Side and Http */
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class LoginController { 
	// Fields

	/* Get the Type Of User */
	private UserSystem userSystem;
	
	/* Map with Key and Value For Create Session */
	private Map<String, ClientSession> tokensMap;
	
	/* Max connection */
	private static final int LENGTH_TOKEN = 15;

	// Constructor initializing Fields
	/* Qualifier is for telling Spring that its connection to component with bean */
	@Autowired
	public LoginController(UserSystem userSystem, @Qualifier("tokens") Map<String, ClientSession> tokensMap) {
		this.userSystem = userSystem;
		this.tokensMap = tokensMap;
	}

	
	/**
	 * Login with Email and Password
	 * Get Client and token
	 * return UUID in template for User Safety :) 
	 */
	@PostMapping("/login")
	public ResponseEntity<Token> login(@RequestParam String email, @RequestParam String password)
			throws InvalidLoginExeption {

		ClientSession session;

		session = userSystem.login(email, password);
		String token = generateToken();
		System.out.println(token);

		tokensMap.put(token, session);

		return ResponseEntity.ok(new Token(token));

	}

	private String generateToken() {
		return UUID.randomUUID().toString().replaceAll("-", "").substring(0, LENGTH_TOKEN);
	}

}
