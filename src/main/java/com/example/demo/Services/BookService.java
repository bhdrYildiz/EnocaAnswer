package com.example.demo.Services;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.Author;
import com.example.demo.Entity.Book;
import com.example.demo.Repositories.BookRepository;
import com.example.demo.dto.BookDto;

@Service
public class BookService {
	
	@Autowired
	private BookRepository bookRepo;
	
	@Autowired
	private AuthorService authorService;
	
	@Autowired
	private ModelMapper modelMapper;
	 
	public List<Book> getBooks(){
		return bookRepo.findAll();
	}
	
	public BookDto getOneBook(Long id) {
		Book book = modelMapper.map(id, Book.class);
		return modelMapper.map(bookRepo.findById(id), BookDto.class);
	}
	
	public List<Book> getBookByAuthorId(Optional<Long> authorId){
		if(authorId.isPresent())
			return bookRepo.findByAuthorId(authorId);
		else
			return bookRepo.findAll();
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
