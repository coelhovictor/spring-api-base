package br.com.coelhovictor.springapibase.controllers;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.coelhovictor.springapibase.domain.Company;
import br.com.coelhovictor.springapibase.domain.Contract;
import br.com.coelhovictor.springapibase.dtos.CompanyDTO;
import br.com.coelhovictor.springapibase.services.CompanyService;
import br.com.coelhovictor.springapibase.services.ContractService;

@RestController
@RequestMapping(value = "/companies")
public class CompanyController {

	@Autowired
	private CompanyService service;
	
	@Autowired
	private ContractService contractService;

	@GetMapping(value = "/{companyId}")
	public ResponseEntity<Company> find(@PathVariable Integer companyId) {
		return ResponseEntity.ok(service.findById(companyId));
	}
	
	@GetMapping()
	public ResponseEntity<Page<Company>> findPage(
			@RequestParam(value = "page", defaultValue = "0") Integer page, 
			@RequestParam(value = "size", defaultValue = "5") Integer size, 
			@RequestParam(value = "orderBy", defaultValue = "name") String orderBy, 
			@RequestParam(value = "direction", defaultValue = "ASC") String direction) {
		Page<Company> list = service.findAll(page, size, orderBy, direction);
		return ResponseEntity.ok(list);
	}
	
	@GetMapping("/{companyId}/contracts")
	public ResponseEntity<Page<Contract>> findContractsPage(
			@PathVariable Integer companyId,
			@RequestParam(value = "page", defaultValue = "0") Integer page, 
			@RequestParam(value = "linesPerPage", defaultValue = "5") Integer linesPerPage, 
			@RequestParam(value = "orderBy", defaultValue = "startDate") String orderBy, 
			@RequestParam(value = "direction", defaultValue = "ASC") String direction) {
		Page<Contract> list = contractService.findByCompany(companyId, page, linesPerPage, 
				orderBy, direction);
		return ResponseEntity.ok(list);
	}
	
	@PostMapping()
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<Void> insert(@Valid @RequestBody CompanyDTO objDTO) {
		Company obj = service.fromDTO(objDTO);
		service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{companyId}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping("/{companyId}")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<Void> update(@PathVariable Integer companyId, 
			@Valid @RequestBody CompanyDTO objDTO) {
		Company obj = service.fromDTO(objDTO);
		obj.setId(companyId);
		obj = service.update(obj);
		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping("/{companyId}")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<Void> delete(@PathVariable Integer companyId) {
		service.delete(companyId);
		return ResponseEntity.noContent().build();
	}
	
}
