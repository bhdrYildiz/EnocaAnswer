package com.example.demo.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Entity.Author;
import com.example.demo.Entity.Book;
import com.example.demo.Repositories.AuthorRepository;
import com.example.demo.Repositories.BookRepository;
import com.example.demo.Services.BookService;
import com.example.demo.dto.BookDto;

@RestController
@RequestMapping("books")
public class BookController {
	
	/*@Autowired
	BookRepository bookRep;
	
	@Autowired
	AuthorRepository authorRep;*/
	@Autowired
	private BookService bookService;
	
	@GetMapping
	public List<BookDto> getBooks(){
		return bookService.getBooks();
	}
	
	@GetMapping("{id}")
	public BookDto getBook(@PathVariable Long id){
		return bookService.getOneBook(id);
	}
	
	@GetMapping("author/{author_id}")
	List<BookDto> getBooksByAuthor(@RequestParam Optional<Long> author_id){
		return bookService.getBookByAuthorId(author_id);
	}
	
	@PostMapping("new/{id}")
	String newBook(@PathVariable Long id,@RequestBody Book book) {
		return bookService.newBook(id, book);
		
	}
	
	@PostMapping("delete/{id}")
	String deleteBook(@PathVariable Long id) {
		return bookService.deleteBook(id);
	}
}
