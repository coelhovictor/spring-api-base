package br.com.coelhovictor.springapibase.dtos;

import java.io.Serializable;

public class AuthDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private String username;
	private String password;
	
	public AuthDTO() {
	}

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