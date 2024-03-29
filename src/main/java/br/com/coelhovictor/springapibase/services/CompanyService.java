package br.com.coelhovictor.springapibase.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.com.coelhovictor.springapibase.domain.Address;
import br.com.coelhovictor.springapibase.domain.Company;
import br.com.coelhovictor.springapibase.domain.Owner;
import br.com.coelhovictor.springapibase.dtos.CompanyDTO;
import br.com.coelhovictor.springapibase.repositories.CompanyRepository;
import br.com.coelhovictor.springapibase.services.exceptions.DataIntegrityException;
import br.com.coelhovictor.springapibase.services.exceptions.NotFoundException;

@Service
public class CompanyService {

	@Autowired
	private CompanyRepository repository;
	
	@Autowired
	private CountryService countryService;
	
	@Autowired
	private AddressService addressService;
	
	@Autowired
	private OwnerService ownerService;

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
	
	public Page<Company> findByOwner(Integer ownerId, Integer page, 
			Integer linesPerPage, String orderBy, String direction) {
		Owner owner = ownerService.findById(ownerId);
		
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, 
				Direction.valueOf(direction), orderBy);
		return repository.findByOwner(owner, pageRequest);
	}
	
	public void insert(Company obj) {
		obj.setId(null);
		obj.getAddress().setCompany(obj);
		
		repository.save(obj);
	}

	public Company update(Company obj) {
		Company newObj = findById(obj.getId());
		updateData(newObj, obj);
		return repository.save(newObj);
	}
	
	public void delete(Integer id) {
		findById(id);
		try {
			repository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Cannot delete a company "
					+ "that has contracts");
		}
	}
	
	public Company fromDTO(CompanyDTO objDTO) {
		Address address = addressService.fromDTO(objDTO.getAddress());
		address.setId(null);
		
		return new Company(null, objDTO.getName(), objDTO.getName(), 
				objDTO.getFoundationDate(), 
				countryService.findById(objDTO.getCountryId()), address,
				ownerService.findById(objDTO.getOwnerId()));
	}
	
	private void updateData(Company newObj, Company obj) {
		newObj.setName(obj.getName());
		newObj.setShortName(obj.getShortName());
		newObj.setFoundationDate(obj.getFoundationDate());
		newObj.setCountry(obj.getCountry());
		obj.getAddress().setId(newObj.getId());
		newObj.setAddress(obj.getAddress());
		newObj.setOwner(obj.getOwner());
	}
	
}
