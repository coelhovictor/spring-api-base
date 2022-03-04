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

import br.com.coelhovictor.springapibase.domain.Country;
import br.com.coelhovictor.springapibase.dtos.CountryDTO;
import br.com.coelhovictor.springapibase.services.CountryService;

@RestController
@RequestMapping(value = "/countries")
public class CountryController {

	@Autowired
	private CountryService service;

	@GetMapping(value = "/{countryId}")
	public ResponseEntity<Country> find(@PathVariable Integer countryId) {
		return ResponseEntity.ok(service.findById(countryId));
	}
	
	@GetMapping()
	public ResponseEntity<Page<Country>> findPage(
			@RequestParam(value = "page", defaultValue = "0") Integer page, 
			@RequestParam(value = "size", defaultValue = "5") Integer size, 
			@RequestParam(value = "orderBy", defaultValue = "name") String orderBy, 
			@RequestParam(value = "direction", defaultValue = "ASC") String direction) {
		Page<Country> list = service.findAll(page, size, orderBy, direction);
		return ResponseEntity.ok(list);
	}

	@PostMapping()
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<Void> insert(@Valid @RequestBody CountryDTO objDTO) {
		Country obj = service.fromDTO(objDTO);
		service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{countryId}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping("/{countryId}")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<Void> update(@PathVariable Integer countryId, 
			@Valid @RequestBody CountryDTO objDTO) {
		Country obj = service.fromDTO(objDTO);
		obj.setId(countryId);
		obj = service.update(obj);
		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping("/{countryId}")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<Void> delete(@PathVariable Integer countryId) {
		service.delete(countryId);
		return ResponseEntity.noContent().build();
	}
	
}
