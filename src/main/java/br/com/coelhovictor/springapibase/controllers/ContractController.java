package br.com.coelhovictor.springapibase.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.coelhovictor.springapibase.domain.Contract;
import br.com.coelhovictor.springapibase.services.ContractService;

@RestController
@RequestMapping(value = "/contracts")
public class ContractController {

	@Autowired
	private ContractService service;

	@GetMapping(value = "/{id}")
	public ResponseEntity<Contract> find(@PathVariable Integer id) {
		return ResponseEntity.ok(service.findById(id));
	}
	
	@GetMapping()
	public ResponseEntity<Page<Contract>> findPage(
			@RequestParam(value = "page", defaultValue = "0") Integer page, 
			@RequestParam(value = "linesPerPage", defaultValue = "5") Integer linesPerPage, 
			@RequestParam(value = "orderBy", defaultValue = "startDate") String orderBy, 
			@RequestParam(value = "direction", defaultValue = "ASC") String direction) {
		Page<Contract> list = service.findAll(page, linesPerPage, orderBy, direction);
		return ResponseEntity.ok(list);
	}
	
}