package com.example.demo.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.User;
import com.example.demo.Repositories.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	public User getOneUserByUserName(String userName) {
		return userRepository.findByUserName(userName);
	}
	
	public User saveOneUser(User newUser) {
		return userRepository.save(newUser);
	}
	
}
