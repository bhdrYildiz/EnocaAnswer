package com.example.demo.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "author")
public class Author {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	private String surname;
	
	/*@OneToMany(mappedBy = "author")
	List<Book> books;
	*/
	
	public Long getid() {
		return id;
	}
	public void setid(Long id) {
		this.id = id;
	}
	
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
