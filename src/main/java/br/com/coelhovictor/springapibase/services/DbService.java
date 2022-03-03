package br.com.coelhovictor.springapibase.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.coelhovictor.springapibase.domain.Address;
import br.com.coelhovictor.springapibase.domain.Company;
import br.com.coelhovictor.springapibase.domain.Contract;
import br.com.coelhovictor.springapibase.domain.Country;
import br.com.coelhovictor.springapibase.domain.Owner;
import br.com.coelhovictor.springapibase.repositories.CompanyRepository;
import br.com.coelhovictor.springapibase.repositories.ContractRepository;
import br.com.coelhovictor.springapibase.repositories.CountryRepository;
import br.com.coelhovictor.springapibase.repositories.OwnerRepository;

@Service
public class DbService {

	@Autowired
	private CompanyRepository companyRepository;
	
	@Autowired
	private CountryRepository countryRepository;
	
	@Autowired
	private ContractRepository contractRepository;
	
	@Autowired
	private OwnerRepository ownerRepository;
	
	public void instantiateTestDatabase() throws ParseException {
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		
		Country ct1 = new Country(null, "Brazil");
		Country ct2 = new Country(null, "United States of America");
		Country ct3 = new Country(null, "France");
		Country ct4 = new Country(null, "Portugal");
		Country ct5 = new Country(null, "Germany");
		
		Address ad1 = new Address(null, "Greifswalder Straße", 212, "Belin", "",
				10405);
		Address ad2 = new Address(null, "Av. República do Chile", 65, "Rio de Janeiro", 
				"Rio de Janeiro", 20031912);
		
		Owner ow1 = new Owner(null, "Lucas von Cranach", null, ct5);
		Owner ow2 = new Owner(null, "Joaquim Silva e Luna", 72, ct1);
		
		Company co1 = new Company(null, "OneFootball GmbH", 
				"OneFootball", dateFormat.parse("02/10/2008"), ct5, ad1, ow1);
		Company co2 = new Company(null, "Petróleo Brasileiro S.A.", 
				"Petrobras", dateFormat.parse("03/10/1953"), ct1, ad2, ow2);
		
		ad1.setCompany(co1);
		ad2.setCompany(co2);
		
		ow1.setCompanies(Arrays.asList(co1));
		ow2.setCompanies(Arrays.asList(co2));
		
		ct5.getCompanies().addAll(Arrays.asList(co1));
		ct1.getCompanies().addAll(Arrays.asList(co2));
		
		ct5.getOwners().addAll(Arrays.asList(ow1));
		ct1.getOwners().addAll(Arrays.asList(ow2));
		
		countryRepository.saveAll(Arrays.asList(ct1, ct2, ct3, ct4, ct5));
		ownerRepository.saveAll(Arrays.asList(ow1, ow2));
		companyRepository.saveAll(Arrays.asList(co1, co2));
		
		Contract cct1 = new Contract(null, dateFormat.parse("04/10/1992"), 
				dateFormat.parse("04/10/2001 00:00"), 2000000.00, co1);
		Contract cct2 = new Contract(null, dateFormat.parse("31/08/2005"), 
				null, 500000.00, co1);
		Contract cct3 = new Contract(null, dateFormat.parse("15/02/2020"), 
				dateFormat.parse("25/10/2021 14:30"), 20000.00, co2);
		
		co1.getContracts().addAll(Arrays.asList(cct1, cct2));
		co2.getContracts().addAll(Arrays.asList(cct3));
		
		contractRepository.saveAll(Arrays.asList(cct1, cct2, cct3));
		
	}
	
}
