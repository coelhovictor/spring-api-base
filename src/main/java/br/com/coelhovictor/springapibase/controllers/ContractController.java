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

import br.com.coelhovictor.springapibase.domain.Contract;
import br.com.coelhovictor.springapibase.dtos.ContractDTO;
import br.com.coelhovictor.springapibase.services.ContractService;

@RestController
@RequestMapping(value = "/contracts")
public class ContractController {

	@Autowired
	private ContractService service;

	@GetMapping(value = "/{contractId}")
	public ResponseEntity<Contract> find(@PathVariable Integer contractId) {
		return ResponseEntity.ok(service.findById(contractId));
	}
	
	@GetMapping()
	public ResponseEntity<Page<Contract>> findPage(
			@RequestParam(value = "page", defaultValue = "0") Integer page, 
			@RequestParam(value = "size", defaultValue = "5") Integer size, 
			@RequestParam(value = "orderBy", defaultValue = "startDate") String orderBy, 
			@RequestParam(value = "direction", defaultValue = "ASC") String direction) {
		Page<Contract> list = service.findAll(page, size, orderBy, direction);
		return ResponseEntity.ok(list);
	}
	
	@PostMapping()
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<Void> insert(@Valid @RequestBody ContractDTO objDTO) {
		Contract obj = service.fromDTO(objDTO);
		service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{contractId}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@PutMapping("/{contractId}")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<Void> update(@PathVariable Integer contractId, 
			@Valid @RequestBody ContractDTO objDTO) {
		Contract obj = service.fromDTO(objDTO);
		obj.setId(contractId);
		obj = service.update(obj);
		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping("/{contractId}")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<Void> delete(@PathVariable Integer contractId) {
		service.delete(contractId);
		return ResponseEntity.noContent().build();
	}
	
}
