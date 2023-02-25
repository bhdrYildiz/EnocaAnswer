package com.example.demo.Repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Entity.Book;

public interface BookRepository extends JpaRepository<Book, Long>{
	
	List<Book> findByAuthorId(Optional<Long> authorId);
	
}
