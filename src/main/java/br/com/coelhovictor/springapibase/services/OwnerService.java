package br.com.coelhovictor.springapibase.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.com.coelhovictor.springapibase.domain.Owner;
import br.com.coelhovictor.springapibase.dtos.OwnerDTO;
import br.com.coelhovictor.springapibase.repositories.OwnerRepository;
import br.com.coelhovictor.springapibase.services.exceptions.DataIntegrityException;
import br.com.coelhovictor.springapibase.services.exceptions.NotFoundException;

@Service
public class OwnerService {

	@Autowired
	private OwnerRepository repository;
	
	@Autowired
	private CountryService countrySevice;

	public Owner findById(Integer id) {
		return repository.findById(id).orElseThrow(() -> 
			new NotFoundException("Owner", id));
	}
	
	public Page<Owner> findAll(Integer page, Integer linesPerPage,
			String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, 
				Direction.valueOf(direction), orderBy);
		return repository.findAll(pageRequest);
	}
	
	public void insert(Owner obj) {
		obj.setId(null);
		repository.save(obj);
	}

	public Owner update(Owner obj) {
		Owner newObj = findById(obj.getId());
		updateData(newObj, obj);
		return repository.save(newObj);
	}
	
	public void delete(Integer id) {
		findById(id);
		try {
			repository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Cannot delete a owner "
					+ "that has companies");
		}
	}
	
	public Owner fromDTO(OwnerDTO objDTO) {
		return new Owner(null, objDTO.getName(), objDTO.getAge(), 
				countrySevice.findById(objDTO.getCountryId()));
	}
	
	private void updateData(Owner newObj, Owner obj) {
		newObj.setName(obj.getName());
		newObj.setAge(obj.getAge());
		newObj.setCountry(obj.getCountry());
	}
	
}
