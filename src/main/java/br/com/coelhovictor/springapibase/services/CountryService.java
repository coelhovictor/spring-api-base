package br.com.coelhovictor.springapibase.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.com.coelhovictor.springapibase.domain.Country;
import br.com.coelhovictor.springapibase.dtos.CountryDTO;
import br.com.coelhovictor.springapibase.repositories.CountryRepository;
import br.com.coelhovictor.springapibase.services.exceptions.DataIntegrityException;
import br.com.coelhovictor.springapibase.services.exceptions.NotFoundException;

@Service
public class CountryService {

	@Autowired
	private CountryRepository repository;

	public Country findById(Integer id) {
		return repository.findById(id).orElseThrow(() -> 
			new NotFoundException("Country", id));
	}
	
	public Page<Country> findAll(Integer page, Integer linesPerPage,
			String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, 
				Direction.valueOf(direction), orderBy);
		return repository.findAll(pageRequest);
	}
	
	public void insert(Country obj) {
		obj.setId(null);
		repository.save(obj);
	}

	public Country update(Country obj) {
		Country newObj = findById(obj.getId());
		updateData(newObj, obj);
		return repository.save(newObj);
	}
	
	public void delete(Integer id) {
		findById(id);
		try {
			repository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Cannot delete a country "
					+ "that has companies");
		}
	}
	
	public Country fromDTO(CountryDTO objDTO) {
		return new Country(null, objDTO.getName());
	}
	
	private void updateData(Country newObj, Country obj) {
		newObj.setName(obj.getName());
	}
	
}
