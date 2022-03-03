package br.com.coelhovictor.springapibase.dtos;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.coelhovictor.springapibase.domain.Owner;

public class OwnerDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	@JsonIgnore
	private Integer id;
	private String name;
	private Integer age;
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
