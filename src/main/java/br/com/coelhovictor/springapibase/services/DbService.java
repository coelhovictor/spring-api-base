package br.com.coelhovictor.springapibase.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.coelhovictor.springapibase.domain.Company;
import br.com.coelhovictor.springapibase.domain.Contract;
import br.com.coelhovictor.springapibase.domain.Country;
import br.com.coelhovictor.springapibase.repositories.CompanyRepository;
import br.com.coelhovictor.springapibase.repositories.ContractRepository;
import br.com.coelhovictor.springapibase.repositories.CountryRepository;

@Service
public class DbService {

	@Autowired
	private CompanyRepository companyRepository;
	
	@Autowired
	private CountryRepository countryRepository;
	
	@Autowired
	private ContractRepository contractRepository;
	
	public void instantiateTestDatabase() throws ParseException {
		
		Country ct1 = new Country(null, "Brazil");
		Country ct2 = new Country(null, "United States of America");
		Country ct3 = new Country(null, "France");
		Country ct4 = new Country(null, "Portugal");
		
		Company co1 = new Company(null, "Company Name LTDA", 
				"Company Name", new Date(System.currentTimeMillis()), ct1);
		Company co2 = new Company(null, "Big Company Name", 
				"Small Name", new Date(System.currentTimeMillis()), ct2);
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		
		Contract cct1 = new Contract(null, dateFormat.parse("04/10/1992 20:00"), 
				dateFormat.parse("04/10/2001 00:00"), 2000000.00, co1);
		Contract cct2 = new Contract(null, dateFormat.parse("31/08/2005 14:30"), 
				null, 500000.00, co1);
		Contract cct3 = new Contract(null, dateFormat.parse("15/02/2020 10:00"), 
				dateFormat.parse("25/10/2021 14:30"), 20000.00, co2);
		
		co1.getContracts().addAll(Arrays.asList(cct1, cct2));
		co2.getContracts().addAll(Arrays.asList(cct3));
		
		ct1.getCompanies().addAll(Arrays.asList(co1));
		ct2.getCompanies().addAll(Arrays.asList(co2));
		
		countryRepository.saveAll(Arrays.asList(ct1, ct2, ct3, ct4));
		companyRepository.saveAll(Arrays.asList(co1, co2));
		contractRepository.saveAll(Arrays.asList(cct1, cct2, cct3));
		
	}
	
}
