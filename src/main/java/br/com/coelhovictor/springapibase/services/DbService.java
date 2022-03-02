package br.com.coelhovictor.springapibase.services;

import java.util.Arrays;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.coelhovictor.springapibase.domain.Company;
import br.com.coelhovictor.springapibase.repositories.CompanyRepository;

@Service
public class DbService {

	@Autowired
	private CompanyRepository companyRepository;
	
	public void instantiateTestDatabase() {
		
		Company co1 = new Company(null, "Company Name LTDA", 
				"Company Name", new Date(System.currentTimeMillis()));
		Company co2 = new Company(null, "Big Company Name", 
				"Small Name", new Date(System.currentTimeMillis()));
		
		companyRepository.saveAll(Arrays.asList(co1, co2));
		
	}
	
}
