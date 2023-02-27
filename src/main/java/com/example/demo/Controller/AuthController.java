package com.example.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Entity.User;
import com.example.demo.Services.UserService;
import com.example.demo.security.JwtTokenProvider;

@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtTokenProvider jwtTokenProvider;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@PostMapping("/login")
	public String login(@RequestBody User userLogin) {
		UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken
				(userLogin.getUserName(), userLogin.getPassword());
		
		Authentication auth = authenticationManager.authenticate(authToken);
		SecurityContextHolder.getContext().setAuthentication(auth);
		String jwtToken = jwtTokenProvider.generateJwtToken(auth);
		return "Bearer " +jwtToken;
		
	}
	
	@PostMapping("/register")
	public String register(@RequestBody User userRegister) {
		if(userService.getOneUserByUserName(userRegister.getUserName()) != null) {
			return "UserName already in use.";
		}
		User user = new User();
		user.setUserName(userRegister.getUserName());
		user.setPassword(passwordEncoder.encode(userRegister.getPassword()));
		userService.saveOneUser(user);
		
		return "User succesfully registered!";
		
	}
	
}
