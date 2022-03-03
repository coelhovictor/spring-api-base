package br.com.coelhovictor.springapibase.dtos;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.coelhovictor.springapibase.domain.Address;
import br.com.coelhovictor.springapibase.services.validation.AddressValid;

@AddressValid
public class AddressDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	@JsonIgnore
	private Integer id;
	
	@NotEmpty(message = "Required field")
	@Length(min = 5, max = 50, message = "The length must be "
			+ "between 5 and 50 characters")
	private String name;
	
	@NotNull(message = "Required field")
	private Integer number;
	
	@NotEmpty(message = "Required field")
	@Length(min = 5, max = 50, message = "The length must be "
			+ "between 5 and 50 characters")
	private String city;
	
	@Length(max = 50, message = "The range has a maximum "
			+ "of 50 characters")
	private String state;
	
	@NotNull(message = "Required field")
	private Integer zip;
	
	public AddressDTO() {
	}
	
	public AddressDTO(Address obj) {
		this.id = obj.getId();
		this.name = obj.getName();
		this.number = obj.getNumber();
		this.city = obj.getCity();
		this.state = obj.getState();
		this.zip = obj.getZip();
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

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Integer getZip() {
		return zip;
	}

	public void setZip(Integer zip) {
		this.zip = zip;
	}
	
}
