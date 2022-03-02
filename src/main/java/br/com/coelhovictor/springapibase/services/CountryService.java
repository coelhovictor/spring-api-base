package br.com.coelhovictor.springapibase.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.coelhovictor.springapibase.domain.Country;
import br.com.coelhovictor.springapibase.repositories.CountryRepository;
import br.com.coelhovictor.springapibase.services.exceptions.NotFoundException;

@Service
public class CountryService {

	@Autowired
	private CountryRepository repository;

	public Country findById(Integer id) {
		return repository.findById(id).orElseThrow(() -> 
			new NotFoundException("Country", id));
	}
	
}
