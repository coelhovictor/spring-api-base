package br.com.coelhovictor.springapibase.dtos;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.coelhovictor.springapibase.domain.Owner;

public class OwnerDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	@JsonIgnore
	private Integer id;
	
	@NotEmpty(message = "Required field")
	@Length(min = 3, max = 50, message = "The length must be "
			+ "between 5 and 50 characters")
	private String name;
	
	@Range(min = 12, max = 150, message = "The range must be " 
			+ "between 12 and 150")
	private Integer age;
	
	@NotNull(message = "Required field")
	private Integer countryId;
	
	public OwnerDTO() {
	}
	
	public OwnerDTO(Owner obj) {
		this.id = obj.getId();
		this.name = obj.getName();
		this.age = obj.getAge();
		this.countryId = obj.getCountry().getId();
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

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Integer getCountryId() {
		return countryId;
	}

	public void setCountryId(Integer countryId) {
		this.countryId = countryId;
	}
	
}
