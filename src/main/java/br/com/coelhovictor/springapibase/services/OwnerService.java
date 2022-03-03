package br.com.coelhovictor.springapibase.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.coelhovictor.springapibase.domain.Owner;
import br.com.coelhovictor.springapibase.repositories.OwnerRepository;
import br.com.coelhovictor.springapibase.services.exceptions.NotFoundException;

@Service
public class OwnerService {

	@Autowired
	private OwnerRepository repository;

	public Owner findById(Integer id) {
		return repository.findById(id).orElseThrow(() -> 
			new NotFoundException("Owner", id));
	}
	
}
