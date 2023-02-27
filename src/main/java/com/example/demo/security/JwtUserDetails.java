package com.example.demo.security;

import java.util.ArrayList;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.demo.Entity.User;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JwtUserDetails implements UserDetails{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public Long id;
	private String username;
	private String password;
	private Collection<? extends GrantedAuthority> authorities;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	  public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}
	
	public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
		this.authorities = authorities;
	}

	public void setUserName(String username) {
		this.username = username;
	}
	
	public String getUsername() {
		return username;
	}

	private JwtUserDetails(Long id, String username, String password, Collection<? extends GrantedAuthority> authorities) {
	        this.id = id;
	        this.username = username;
	        this.password = password;
	        this.authorities = authorities;
	    }

	    public static JwtUserDetails create(User user) {
	        List<GrantedAuthority> authoritiesList = new ArrayList<>();
	        authoritiesList.add(new SimpleGrantedAuthority("user"));
	        return new JwtUserDetails(user.getId(), user.getUserName(), user.getPassword(), authoritiesList);
	    }


	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}


}
