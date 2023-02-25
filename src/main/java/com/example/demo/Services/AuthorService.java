package com.example.demo.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.Author;
import com.example.demo.Repositories.AuthorRepository;

@Service
public class AuthorService {

	@Autowired
	private AuthorRepository authorRepo;
	
	public List<Author> getAuthors(){
		return authorRepo.findAll();
	}
	
	public Author getAuthor(Long id) {
		return authorRepo.findById(id).orElse(null);
	}
	
	public Author newAuthor(Author author) {
		return authorRepo.save(author);
	}
	
	public Author updateAuthor(Long id, Author updateAuthor) {
		
		Optional<Author> author = authorRepo.findById(id);
		if(author.isPresent()) {
			Author toUpdate = author.get();
			toUpdate.setName(updateAuthor.getName());
			toUpdate.setSurname(updateAuthor.getSurname());
			authorRepo.save(toUpdate);
			return toUpdate;
		}
		else
			return null;
	}
	
	public String deleteOneAuthor(Long id) {
		authorRepo.deleteById(id);
		return "Author is deleted!";
	}
	
	
 	
	
	
}
