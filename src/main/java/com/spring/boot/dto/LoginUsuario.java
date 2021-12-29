package com.spring.boot.dto;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;

public class LoginUsuario {

	@NotEmpty
	private String username;	
	@NotEmpty
	@Column(length = 60)
	private String password;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
