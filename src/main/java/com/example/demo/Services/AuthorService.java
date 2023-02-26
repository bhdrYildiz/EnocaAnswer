package com.example.demo.Services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.Author;
import com.example.demo.Repositories.AuthorRepository;
import com.example.demo.dto.AuthorDto;

@Service
public class AuthorService {

	@Autowired
	private AuthorRepository authorRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	public List<AuthorDto> getAuthors(){
		List<Author> authors = authorRepo.findAll();
		List<AuthorDto> authorDto = authors.stream()
				.map(author -> modelMapper.map(author, AuthorDto.class)).collect(Collectors.toList());
		return authorDto;
	}
	
	public AuthorDto getAuthor(Long id) {
		Author author = modelMapper.map(id,Author.class);
		return modelMapper.map(authorRepo.findById(id), AuthorDto.class);
	}
	
	public AuthorDto newAuthor(AuthorDto authorDto) {
		Author author = modelMapper.map(authorDto, Author.class);
		author.setName(authorDto.getName());
		author.setSurname(authorDto.getSurname());
		return modelMapper.map(authorRepo.save(author), AuthorDto.class);
	}
	
	public AuthorDto updateAuthor(Long id, AuthorDto updateauthor) {
		
		Optional<Author> author = authorRepo.findById(id);
		if(author.isPresent()) {
			Author toUpdate = author.get();
			toUpdate.setName(updateauthor.getName());
			toUpdate.setSurname(updateauthor.getSurname());
			return modelMapper.map(authorRepo.save(toUpdate), AuthorDto.class);
		}
		else
			throw new RuntimeException("Author id not found!" +id);
	}
	
	public String deleteOneAuthor(Long id) {
		authorRepo.deleteById(id);
		return "Author is deleted!";
	}
	
}
