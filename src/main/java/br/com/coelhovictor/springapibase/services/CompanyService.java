package br.com.coelhovictor.springapibase.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.coelhovictor.springapibase.domain.Company;
import br.com.coelhovictor.springapibase.repositories.CompanyRepository;

@Service
public class CompanyService {

	@Autowired
	private CompanyRepository repository;

	public Company findById(Integer id) {
		return repository.findById(id).orElse(null);
	}

}
