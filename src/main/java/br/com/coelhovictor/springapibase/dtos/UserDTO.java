package br.com.coelhovictor.springapibase.dtos;

import java.io.Serializable;
import java.util.Date;

import br.com.coelhovictor.springapibase.domain.User;

public class UserDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;
	private String username;
	private String email;
	private String password;
	private Date birthday;
	
	public UserDTO() {
	}
	
	public UserDTO(User obj) {
		this.id = obj.getId();
		this.username = obj.getUsername();
		this.email = obj.getEmail();
		this.password = obj.getPassword();
		this.birthday = obj.getBirthday();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	
}
