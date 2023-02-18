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
import com.example.demo.Entity.Book;
import com.example.demo.Repositories.AuthorRepository;
import com.example.demo.Repositories.BookRepository;

@RestController
@RequestMapping("books")
public class BookController {
	
	@Autowired
	BookRepository bookRep;
	
	@Autowired
	AuthorRepository authorRep;
	
	@GetMapping
	public List<Book> getBooks(){
		return bookRep.findAll();
	}
	
	
	@GetMapping("{id}")
	Optional<Book> getBook(@PathVariable Long id){
		return bookRep.findById(id);
	}
	
	
	@GetMapping("author/{author_id}")
	List<Book> getBooksByAuthor(@PathVariable Long author_id){
		return bookRep.findByAuthorId(author_id);
	}
	
	@PostMapping("new/{id}")
	String newBook(@PathVariable Long id,@RequestBody Book book) {
		
		for (Author a : authorRep.findAll()) {
			if(a.getid()==id) {
				book.setAuthor(a);
				bookRep.save(book);
				return "Book Saved!";
			}
		}
		return "no such author in database!";
	}
	
	@PostMapping("delete/{id}")
	String deleteBook(@PathVariable Long id) {
		
		bookRep.deleteById(id);
		return "book deleted!";
	}
}
