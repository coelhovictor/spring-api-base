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
import br.com.coelhovictor.springapibase.domain.Owner;
import br.com.coelhovictor.springapibase.dtos.OwnerDTO;
import br.com.coelhovictor.springapibase.services.CompanyService;
import br.com.coelhovictor.springapibase.services.OwnerService;

@RestController
@RequestMapping(value = "/owners")
public class OwnerController {

	@Autowired
	private OwnerService service;
	
	@Autowired
	private CompanyService companyService;

	@GetMapping(value = "/{ownerId}")
	public ResponseEntity<Owner> find(@PathVariable Integer ownerId) {
		return ResponseEntity.ok(service.findById(ownerId));
	}
	
	@GetMapping()
	public ResponseEntity<Page<Owner>> findPage(
			@RequestParam(value = "page", defaultValue = "0") Integer page, 
			@RequestParam(value = "size", defaultValue = "5") Integer size, 
			@RequestParam(value = "orderBy", defaultValue = "name") String orderBy, 
			@RequestParam(value = "direction", defaultValue = "ASC") String direction) {
		Page<Owner> list = service.findAll(page, size, orderBy, direction);
		return ResponseEntity.ok(list);
	}
	
	@GetMapping("/{ownerId}/companies")
	public ResponseEntity<Page<Company>> findCompaniesPage(
			@PathVariable Integer ownerId,
			@RequestParam(value = "page", defaultValue = "0") Integer page, 
			@RequestParam(value = "size", defaultValue = "5") Integer size, 
			@RequestParam(value = "orderBy", defaultValue = "foundationDate") String orderBy, 
			@RequestParam(value = "direction", defaultValue = "ASC") String direction) {
		Page<Company> list = companyService.findByOwner(ownerId, page, size, 
				orderBy, direction);
		return ResponseEntity.ok(list);
	}

	@PostMapping()
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<Void> insert(@Valid @RequestBody OwnerDTO objDTO) {
		Owner obj = service.fromDTO(objDTO);
		service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{ownerId}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping("/{ownerId}")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<Void> update(@PathVariable Integer ownerId, 
			@Valid @RequestBody OwnerDTO objDTO) {
		Owner obj = service.fromDTO(objDTO);
		obj.setId(ownerId);
		obj = service.update(obj);
		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping("/{ownerId}")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<Void> delete(@PathVariable Integer ownerId) {
		service.delete(ownerId);
		return ResponseEntity.noContent().build();
	}
	
}
