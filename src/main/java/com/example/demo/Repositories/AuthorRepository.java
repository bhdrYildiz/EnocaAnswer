package com.example.demo.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Entity.Author;

public interface AuthorRepository extends JpaRepository<Author, Long> {
	
	
}
