package br.com.coelhovictor.springapibase.dtos;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.coelhovictor.springapibase.domain.Company;

public class CompanyDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;
	
	@NotEmpty(message = "Required field")
	@Length(min = 5, max = 50, message = "The length must be "
			+ "between 5 and 50 characters")
	private String name;
	
	@NotEmpty(message = "Required field")
	@Length(min = 3, max = 30, message = "The length must be "
			+ "between 5 and 30 characters")
	private String shortName;
	
	@JsonFormat(pattern = "MM/dd/yyyy HH:mm")
	private Date foundationDate;
	
	private Integer countryId;
	
	public CompanyDTO() {
	}
	
	public CompanyDTO(Company obj) {
		this.id = obj.getId();
		this.name = obj.getName();
		this.shortName = obj.getShortName();
		this.foundationDate = obj.getFoundationDate();
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

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public Date getFoundationDate() {
		return foundationDate;
	}

	public void setFoundationDate(Date foundationDate) {
		this.foundationDate = foundationDate;
	}

	public Integer getCountryId() {
		return countryId;
	}

	public void setCountryId(Integer countryId) {
		this.countryId = countryId;
	}

}
