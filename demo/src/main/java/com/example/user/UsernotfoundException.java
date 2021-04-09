package com.example.user;import org.apache.logging.log4j.message.Message;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UsernotfoundException extends RuntimeException {

	public UsernotfoundException(String message) {
		
		super(message);
	}
}
