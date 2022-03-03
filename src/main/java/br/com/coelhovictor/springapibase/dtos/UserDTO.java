package br.com.coelhovictor.springapibase.dtos;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.coelhovictor.springapibase.domain.User;
import br.com.coelhovictor.springapibase.services.validation.UserValid;

@UserValid
public class UserDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;
	
	@NotEmpty(message = "Required field")
	@Length(min = 4, max = 42, message = "The length must be "
			+ "between 4 and 42 characters")
	private String name;
	
	@NotEmpty(message = "Required field")
	@Length(min = 5, max = 24, message = "The length must be "
			+ "between 5 and 24 characters")
	private String username;
	
	@NotEmpty(message = "Required field")
	@Email(message = "Must be a valid email")
	private String email;
	
	@NotEmpty(message = "Required field")
	@Length(min = 3, max = 32, message = "The length must be "
			+ "between 3 and 32 characters")
	private String password;
	
	@JsonFormat(pattern = "MM/dd/yyyy")
	private Date birthday;
	
	public UserDTO() {
	}
	
	public UserDTO(User obj) {
		this.id = obj.getId();
		this.name = obj.getName();
		this.username = obj.getUsername();
		this.email = obj.getEmail();
		this.password = obj.getPassword();
		this.birthday = obj.getBirthday();
	}

	@JsonIgnore
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
