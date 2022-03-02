package br.com.coelhovictor.springapibase.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.com.coelhovictor.springapibase.domain.Contract;
import br.com.coelhovictor.springapibase.repositories.ContractRepository;
import br.com.coelhovictor.springapibase.services.exceptions.NotFoundException;

@Service
public class ContractService {

	@Autowired
	private ContractRepository repository;

	public Contract findById(Integer id) {
		return repository.findById(id).orElseThrow(() -> 
			new NotFoundException("Contract", id));
	}
	
	public Page<Contract> findAll(Integer page, Integer linesPerPage,
			String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, 
				Direction.valueOf(direction), orderBy);
		return repository.findAll(pageRequest);
	}
	
}
