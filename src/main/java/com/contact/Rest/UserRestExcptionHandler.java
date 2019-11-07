package com.contact.Rest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.contact.Rest.ex.InvalidLoginExeption;
import com.contact.controller.LoginController;
import com.contact.controller.UserController;
import com.contact.controller.UserErrorResponse;
import com.contact.controller.ex.InvalidListException;
import com.contact.controller.ex.SystemMalfunctionException;
import com.contact.controller.ex.UserMalformExeption;



@ControllerAdvice(assignableTypes = {LoginController.class,UserController.class})
public class UserRestExcptionHandler {
	
	@ExceptionHandler(InvalidLoginExeption.class)
	@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
	@ResponseBody
	public UserErrorResponse handleUnauthrized(InvalidLoginExeption ex) {
		return UserErrorResponse.now(HttpStatus.UNAUTHORIZED,String.format("unauthroized : %s", ex.getMessage()));
	}
	
	@ExceptionHandler(UserMalformExeption.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	@ResponseBody
	public UserErrorResponse handleTaskMalform(UserMalformExeption ex) {
		return UserErrorResponse.now(HttpStatus.BAD_REQUEST,String.format("task malformed : %s", ex.getMessage()));
	}
	
	@ExceptionHandler(InvalidListException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	@ResponseBody
	public UserErrorResponse handleUnauthrized(InvalidListException ex) {
		return UserErrorResponse.now(HttpStatus.BAD_REQUEST,String.format("bad request : %s", ex.getMessage()));
	}
	
	@ExceptionHandler(SystemMalfunctionException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	@ResponseBody
	public UserErrorResponse handleUnauthrized(SystemMalfunctionException ex) {
		return UserErrorResponse.now(HttpStatus.BAD_REQUEST,String.format("bad request: %s", ex.getMessage()));
	}
	

	

}
