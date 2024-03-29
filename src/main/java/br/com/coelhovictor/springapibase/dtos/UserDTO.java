package br.com.coelhovictor.springapibase.dtos;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.coelhovictor.springapibase.domain.User;

public class UserDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;
	
	@NotEmpty(message = "Required field")
	@Length(min = 4, max = 42, message = "The length must be "
			+ "between 4 and 42 characters")
	private String name;

	@NotEmpty(message = "Required field")
	@Length(min = 3, max = 32, message = "The length must be "
			+ "between 3 and 32 characters")
	private String password;
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date birthday;
	
	public UserDTO() {
	}
	
	public UserDTO(User obj) {
		this.id = obj.getId();
		this.name = obj.getName();
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
