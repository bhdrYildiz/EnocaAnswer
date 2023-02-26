package com.example.demo.Controller;

import java.util.List;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Entity.Author;
import com.example.demo.Repositories.AuthorRepository;
import com.example.demo.Services.AuthorService;
import com.example.demo.dto.AuthorDto;

@RestController
@RequestMapping("authors")
public class AuthorController{

	/*@Autowired
	AuthorRepository authorRep;
	*/
	
	@Autowired
	private AuthorService authorService;
	
	@GetMapping
	public List<AuthorDto> getAuthors(){
		
		return authorService.getAuthors();
	}
	
	@GetMapping("/{id}")
	public AuthorDto getAuthor(@PathVariable Long id){
		
		return authorService.getAuthor(id);
	}
	
	@PostMapping("new")
	public AuthorDto newAuthor(@RequestBody AuthorDto author) {
		
		return authorService.newAuthor(author);
	}
	
	@PostMapping("update/{id}")
	public AuthorDto updateAuthor(@PathVariable Long id, @RequestBody AuthorDto updateauthor) {
		
			return authorService.updateAuthor(id, updateauthor);
	} 
	
	@PostMapping("delete/{id}")
	String deleteAuthor(@PathVariable Long id) {
	
		authorService.deleteOneAuthor(id);
		return "Author is deleted!";	
	}
}
