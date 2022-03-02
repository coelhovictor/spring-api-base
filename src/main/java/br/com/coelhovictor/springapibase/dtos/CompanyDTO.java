package br.com.coelhovictor.springapibase.dtos;

import java.io.Serializable;
import java.util.Date;

import br.com.coelhovictor.springapibase.domain.Company;

public class CompanyDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;
	private String name;
	private String shortName;
	private Date foundationDate;
	
	public CompanyDTO() {
	}
	
	public CompanyDTO(Company obj) {
		this.id = obj.getId();
		this.name = obj.getName();
		this.shortName = obj.getShortName();
		this.foundationDate = obj.getFoundationDate();
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

}
