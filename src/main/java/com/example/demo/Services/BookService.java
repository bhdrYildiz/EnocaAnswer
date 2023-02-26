package com.example.demo.Services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.Author;
import com.example.demo.Entity.Book;
import com.example.demo.Repositories.AuthorRepository;
import com.example.demo.Repositories.BookRepository;
import com.example.demo.dto.AuthorDto;
import com.example.demo.dto.BookDto;

@Service
public class BookService {
	
	@Autowired
	private BookRepository bookRepo;
	
	@Autowired
	private AuthorRepository authorRepo;
	
	@Autowired
	private AuthorService authorService;
	
	@Autowired
	private ModelMapper modelMapper;
	 
	public List<BookDto> getBooks(){
		List<Book> books = bookRepo.findAll();
		List<BookDto> bookDtos = books.stream()
				.map(book -> modelMapper.map(book, BookDto.class)).collect(Collectors.toList());
		return bookDtos;
	}
	
	public BookDto getOneBook(Long id) {
		Book book = modelMapper.map(id, Book.class);
		return modelMapper.map(bookRepo.findById(id), BookDto.class);
	}
	
	public List<BookDto> getBookByAuthorId(Optional<Long> authorId){
		List<Book> bookList;
		if(authorId.isPresent()) {
			bookList = bookRepo.findByAuthorId(authorId.get());
		}else {
			throw new RuntimeException("Author id not found!");
		}
		bookList= bookRepo.findAll();
		return bookList.stream().map(book -> modelMapper.map(book, BookDto.class)).collect(Collectors.toList());
	}
	
	public String newBook(Long id, Book book) {
		for(Author a : authorRepo.findAll()) {
			if(a.getid()==id) {
				book.setAuthor(a);
				bookRepo.save(book);
				return "Book saved!";
			}
		}
		throw new RuntimeException("No such an Author in database!");
	}
	
	public String deleteBook(Long id) {
		 bookRepo.deleteById(id);
		 return "Book deleted!";
	}
}
