package com.example.demo.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.Author;
import com.example.demo.Entity.Book;
import com.example.demo.Repositories.BookRepository;

@Service
public class BookService {
	
	@Autowired
	private BookRepository bookRepo;
	
	@Autowired
	private AuthorService authorService;
	 
	public List<Book> getBooks(){
		return bookRepo.findAll();
	}
	
	public Book getOneBook(Long id) {
		
		return bookRepo.findById(id).orElse(null);
	}
	
	public List<Book> getBookByAuthorId(Long authorId){
		return bookRepo.findByAuthorId(authorId);
	}
	
	public String newBook(Long id, Book book) {
		for(Author a : authorService.getAuthors()) {
			if(a.getid()==id) {
				book.setAuthor(a);
				bookRepo.save(book);
				return "Book saved!";
			}else
				return "no such author in database!";
		}
		return "no such author in database!";
	}
	
	public String deleteBook(Long id) {
		 bookRepo.deleteById(id);
		 return "Book deleted!";
	}
	

}
