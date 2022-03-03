package br.com.coelhovictor.springapibase.dtos;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import br.com.coelhovictor.springapibase.domain.Country;

public class CountryDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;
	
	@NotEmpty(message = "Required field")
	@Length(min = 3, max = 50, message = "The length must be "
			+ "between 5 and 50 characters")
	private String name;
	
	public CountryDTO() {
	}
	
	public CountryDTO(Country obj) {
		this.id = obj.getId();
		this.name = obj.getName();
	}

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
	
}
