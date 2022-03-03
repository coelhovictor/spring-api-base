package br.com.coelhovictor.springapibase.dtos;

import java.io.Serializable;

import br.com.coelhovictor.springapibase.domain.Address;

public class AddressDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;
	private String name;
	private String city;
	private String state;
	private Integer zip;
	
	public AddressDTO() {
	}
	
	public AddressDTO(Address obj) {
		this.id = obj.getId();
		this.name = obj.getName();
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
