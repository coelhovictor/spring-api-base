package br.com.coelhovictor.springapibase.services;

import java.util.Arrays;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.coelhovictor.springapibase.domain.Company;
import br.com.coelhovictor.springapibase.domain.Country;
import br.com.coelhovictor.springapibase.repositories.CompanyRepository;
import br.com.coelhovictor.springapibase.repositories.CountryRepository;

@Service
public class DbService {

	@Autowired
	private CompanyRepository companyRepository;
	
	@Autowired
	private CountryRepository countryRepository;
	
	public void instantiateTestDatabase() {
		
		Country ct1 = new Country(null, "Brazil");
		Country ct2 = new Country(null, "United States of America");
		Country ct3 = new Country(null, "France");
		Country ct4 = new Country(null, "Portugal");
		
		Company co1 = new Company(null, "Company Name LTDA", 
				"Company Name", new Date(System.currentTimeMillis()), ct1);
		Company co2 = new Company(null, "Big Company Name", 
				"Small Name", new Date(System.currentTimeMillis()), ct2);
		
		ct1.getCompanies().add(co1);
		ct2.getCompanies().add(co2);
		
		countryRepository.saveAll(Arrays.asList(ct1, ct2, ct3, ct4));
		companyRepository.saveAll(Arrays.asList(co1, co2));
		
	}
	
}
