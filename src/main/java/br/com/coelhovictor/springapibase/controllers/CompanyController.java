package br.com.coelhovictor.springapibase.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.coelhovictor.springapibase.domain.Company;
import br.com.coelhovictor.springapibase.services.CompanyService;

@RestController
@RequestMapping(value = "/companies")
public class CompanyController {

	@Autowired
	private CompanyService service;

	@GetMapping(value = "/{id}")
	public ResponseEntity<Company> find(@PathVariable Integer id) {
		return ResponseEntity.ok(service.findById(id));
	}
	
}
