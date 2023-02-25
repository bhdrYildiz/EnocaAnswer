package com.example.demo.dto;

import lombok.Data;

@Data
public class AuthorDto {

	private String name;
	
	private String surname;
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getSurname() {
		return surname;
	}
	
	public void setSurname(String surname) {
		this.surname = surname;
	}

}
