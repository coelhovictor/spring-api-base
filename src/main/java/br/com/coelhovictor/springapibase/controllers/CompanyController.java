package br.com.coelhovictor.springapibase.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.coelhovictor.springapibase.domain.Company;
import br.com.coelhovictor.springapibase.dtos.CompanyDTO;
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
	
	@GetMapping()
	public ResponseEntity<Page<CompanyDTO>> findPage(
			@RequestParam(value = "page", defaultValue = "0") Integer page, 
			@RequestParam(value = "linesPerPage", defaultValue = "5") Integer linesPerPage, 
			@RequestParam(value = "orderBy", defaultValue = "name") String orderBy, 
			@RequestParam(value = "direction", defaultValue = "ASC") String direction) {
		Page<Company> list = service.findAll(page, linesPerPage, orderBy, direction);
		Page<CompanyDTO> listDto = list.map(obj -> new CompanyDTO(obj));
		return ResponseEntity.ok(listDto);
	}
	
}
