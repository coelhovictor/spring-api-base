package br.com.coelhovictor.springapibase.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Company implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;
	private String shortName;
	
	@JsonFormat(pattern = "MM/dd/yyyy")
	private Date foundationDate;
	
	@ManyToOne
	@JoinColumn(name = "country_id")
	private Country country;
	
	@JsonIgnore
	@OneToMany(mappedBy = "company")
	private List<Contract> contracts = new ArrayList<>();
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "address_id")
	private Address address;
	
	@ManyToOne
	@JoinColumn(name = "owner_id")
	private Owner owner;
	
	public Company() {
	}

	public Company(Integer id, String name, String shortName, 
			Date foundationDate, Country country, Address address, 
			Owner owner) {
		super();
		this.id = id;
		this.name = name;
		this.shortName = shortName;
		this.foundationDate = foundationDate;
		this.country = country;
		this.address = address;
		this.owner = owner;
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

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public List<Contract> getContracts() {
		return contracts;
	}

	public void setContracts(List<Contract> contracts) {
		this.contracts = contracts;
	}
	
	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Owner getOwner() {
		return owner;
	}

	public void setOwner(Owner owner) {
		this.owner = owner;
	}

	public Double getContractsTotalValue() {
		double totalValue = 0;
		for(Contract con : this.contracts) {
			totalValue += con.getValue();
		}
		return totalValue;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Company other = (Company) obj;
		return Objects.equals(id, other.id);
	}
	
}
