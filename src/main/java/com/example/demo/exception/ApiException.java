package com.example.demo.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


public class ApiException {
	
	private LocalDateTime timestamp;
	
	private String message;
	
	private String code;
	
	public ApiException(LocalDateTime timestamp, String message, String code) {
		this.code = code;
		this.message = message;
		this.timestamp = timestamp;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
}
