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

@RestController
@RequestMapping("authors")
public class AuthorController{

	@Autowired
	AuthorRepository authorRep;
	
	@GetMapping
	public List<Author> getAuthors(){
		return authorRep.findAll();
	}
	
	@GetMapping("/{id}")
	Optional<Author> getAuthor(@PathVariable Long id){
		
		return authorRep.findById(id);
	}
	
	@PostMapping("new")
	String newAuthor(@RequestBody Author author) {
		
		authorRep.save(author);
		return "Author Saved!";
	}
	@PostMapping("update/{id}")
	public String updateAuthor(@PathVariable Long id, @RequestBody Author updateauthor) {
		Optional<Author> author = authorRep.findById(id);
		if(author.isPresent()) {
			Author toupdate = author.get();
			toupdate.setName(updateauthor.getName());
			toupdate.setSurname(updateauthor.getSurname());
			authorRep.save(toupdate);
			return "author is update now!";
		}
		else
			return "author can't updated";
	}
	
	@PostMapping("delete/{id}")
	String deleteAuthor(@PathVariable Long id) {
		
		authorRep.deleteById(id);
		return "author deleted!";
	}
}
