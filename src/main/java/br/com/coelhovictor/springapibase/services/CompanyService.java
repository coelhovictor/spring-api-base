package br.com.coelhovictor.springapibase.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.com.coelhovictor.springapibase.domain.Company;
import br.com.coelhovictor.springapibase.dtos.CompanyDTO;
import br.com.coelhovictor.springapibase.repositories.CompanyRepository;
import br.com.coelhovictor.springapibase.services.exceptions.NotFoundException;

@Service
public class CompanyService {

	@Autowired
	private CompanyRepository repository;
	
	@Autowired
	private CountryService countryService;

	public Company findById(Integer id) {
		return repository.findById(id).orElseThrow(() -> 
			new NotFoundException("Company", id));
	}
	
	public Page<Company> findAll(Integer page, Integer linesPerPage,
			String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, 
				Direction.valueOf(direction), orderBy);
		return repository.findAll(pageRequest);
	}
	
	public void insert(Company obj) {
		obj.setId(null);
		repository.save(obj);
	}

	public Company fromDTO(CompanyDTO objDTO) {
		return new Company(null, objDTO.getName(), objDTO.getName(), 
				objDTO.getFoundationDate(), countryService.findById(objDTO.getCountryId()));
	}
	
}
